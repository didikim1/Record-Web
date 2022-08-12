package com.lab603.record.web.makeTTS.act;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

import org.neo4j.cypher.internal.compiler.v1_9.parser.ParsedNamedPath;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lab603.record.web.comtn.biz.TTSServerInfoBiz;
import com.lab603.record.web.comtn.biz.dto.TTSWavDTO;
import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.beans.FrameworkBeans;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.result.ResultCode;
import com.lab603.record.web.framework.result.ResultMessage;
import com.lab603.record.web.makeTTS.biz.MakeTTSBiz;
import com.lab603.record.web.trunk.biz.TrunkBiz;

import scala.reflect.internal.Trees.New;


@Controller
@RequestMapping("/makeTTS")
public class MakeTTSAct 
{
	final String pagePrefix = "makeTTS";

	private static final Logger logger = LoggerFactory.getLogger(MakeTTSAct.class);
	
	@Resource(name="com.lab603.record.web.makeTTS.biz.MakeTTSBiz")
	MakeTTSBiz mBiz;
	
	@Resource(name="com.lab603.record.web.comtn.biz.TTSServerInfoBiz")
	TTSServerInfoBiz TTSServerInfoBizmBiz;
	
	@RequestMapping(value = { "/" ,  "/ListPagingData.do" })
	public String ListPagingData(Model model)
	{
		MyMap 			 paramMap 		  	  = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		BasicBean 		 resultBean  	      = null;
		
		resultBean = mBiz.ListPagingData( paramMap );
		
		model.addAttribute("Data", 					resultBean);
		model.addAttribute("paramMap",  			paramMap);

		return pagePrefix + "/ListPagingData";
	}
	

	@RequestMapping(value = { "/SelectOneData.do" })
	public String SelectOneData(Model model)
	{
		MyMap                 paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
        MyCamelMap     		  resultMap       = new MyCamelMap();

        resultMap = mBiz.SelectOneData(paramMap);

        model.addAttribute("paramMap",      paramMap);
        model.addAttribute("Data",          resultMap);

		return pagePrefix + "/SelectOneData";
	}
	
	
	/**
	 * tts생성 model
	 */
	@RequestMapping(value = { "/RegisterContent.do" })
	public String RegisterContent(Model model)
	{
		MyMap paramMap        = FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyMap resultMap       = null;

		resultMap  = mBiz.SelectOneData(paramMap);

		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);
		System.out.println("resultMap : " + resultMap);

		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);

		return pagePrefix + "/RegisterContent";
	}

	/**
	 *  TTS 등록 (DB저장)
	 */
	@RequestMapping(value = { "/RegisterData.do" })
	public @ResponseBody ResultMessage RegisterData(Model model, @RequestParam String ttsMent)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;

		resultRegisterDataCount = mBiz.RegisterData( paramMap );

		resultMap.put("ResultDataCount", resultRegisterDataCount);
		
		TTSWavDTO dto = TTSServerInfoBizmBiz.ttsRealMake(ttsMent);

		System.out.println( dto.toString());
		System.out.println( dto.toString());

		resultMap.put("result", dto.toString());

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);

	}

	@RequestMapping(value = { "/ModifyData.do" })
	public @ResponseBody ResultMessage ModifyData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultModifyDataCount 	= 0;

		resultModifyDataCount = mBiz.ModifyData( paramMap );

		resultMap.put("ResultDataCount", resultModifyDataCount);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	}
	

	/**
	 * tts 삭제
	 */
	@RequestMapping(value = { "/DeleteData.do" })
	public @ResponseBody ResultMessage DeleteData(Model model)
	{
		MyMap 	paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		String 	resultCode			  		= ResultCode.RESULT_INTERNAL_SERVER_ERROR;
		int   	resultRegisterDataCount 	= 0;

		resultRegisterDataCount = mBiz.DeleteData( paramMap );

		if( resultRegisterDataCount > 0)
		{
			resultCode			  		= ResultCode.RESULT_OK;
		}

		 model.addAttribute("paramMap",      paramMap);

		 return new ResultMessage(ResultCode.RESULT_OK, null);
	}
	
	/**
	 * 파일업로드
	 */
	@PostMapping(value = {"/uploadFile.do" })
	public @ResponseBody ResultMessage uploadFile ( HttpServletRequest request, @RequestParam("fileName") MultipartFile[] fileName, @RequestParam String ttsTitle)
	{
		
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;
		
//		String saveDir = request.getSession().getServletContext().getRealPath("D:/Temp50/upload"+orifileName);
		String Dir = "D:/Temp50/upload/";
		
        for(MultipartFile multipartFile : fileName) {
            if(!multipartFile.isEmpty()) {
                String orifileName = multipartFile.getOriginalFilename();
                String ext = orifileName.substring(orifileName.lastIndexOf(".")); 

                // 파일 저장
                try {
                	multipartFile.transferTo(new File(Dir+ orifileName + ext));
                	
                		paramMap.put("ttsTitle" , ttsTitle); // 타이틀
                		paramMap.put("ivrPath" , Dir); // 파일이 저장된 주소
	                	resultRegisterDataCount = mBiz.RegisterData( paramMap );
	                	
                }catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResultMessage(ResultCode.RESULT_OK, null);
  }
	
	/**
	 * TTS생성(임시파일)
	 */
	@RequestMapping(value = {"/makeFile.do" })
	public @ResponseBody ResultMessage MakeFile(Model model, @RequestParam String ttsMent)
	{
		MyMap 	paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		
		System.out.println("@@@");
		
		TTSWavDTO dto = TTSServerInfoBizmBiz.ttsMake(ttsMent);

		System.out.println( dto.toString());
		System.out.println( dto.toString());

		resultMap.put("result", dto.toString());

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	} 
	
	/**
	 * 임시저장 tts 파일삭제 
	 */
	@RequestMapping(value = {"/DelectFile.do" })
	public @ResponseBody ResultMessage DelectFile(Model model)
	{
		
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		
		File rootDir 	= new File("D:/Temp50/Pass/");
		
	    File[] folders 	= rootDir.listFiles();
	      if (folders != null) 
	      {
	         for (File folder : folders) 
	         {
	            File[] files = folder.listFiles();
	            for (File file : files) 
	            {
		            File wavFile = new File(file.getPath());
		            if (file.exists()) {
		            	System.out.println("Remove file: " + wavFile.getPath());
		            	wavFile.delete();
		            }
	            }
	         } 
	      }
	      return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	    }
	
}