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
	public String uploadFile ( HttpServletRequest request, @RequestParam("fileName") MultipartFile[] fileName)
	{
		String saveDir = request.getSession().getServletContext().getRealPath("D:/Temp50/upload");

		File dir = new File(saveDir);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        for(MultipartFile multipartFile : fileName) {
            if(!multipartFile.isEmpty()) {
                // 기존 파일 이름을 받고 확장자 저장
                String orifileName = multipartFile.getOriginalFilename();
                String ext = orifileName.substring(orifileName.lastIndexOf("."));

                // 파일 저장
                try {
                	multipartFile.transferTo(new File(saveDir + "/" + fileName));
                }catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
      return "uploadEnd";
  }
	
//		List<File> list = new ArrayList<>();
//
//		for (MultipartFile fileName : uploadfile) {			
//			File dto = new File( fileName.getOriginalFilename(), fileName.getContentType() );	
//			list.add(dto);
//			File newFileName = new File("D:/Temp50/" + dto.getName());
//			
//			TTSWavDTO TTsDto = TTSServerInfoBizmBiz.uploadFile(fileName);
//			
//			try {
//				fileName.transferTo(newFileName);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		model.addAttribute("files", list);
//		return "";
		
//		MyMap 	paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
//		MyCamelMap 	resultMap 				= new MyCamelMap();
//		
//		System.out.println("@@@");
//		
//		TTSWavDTO dto = TTSServerInfoBizmBiz.ttsMake(ttsMent);
//		
//		System.out.println( dto.toString());
//		System.out.println( dto.toString());
//		
//		resultMap.put("result", dto.toString());
//		
//		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
	
	/**
	 *  
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
	 * 임시파일삭제 
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
	
//	@PostMapping("/UploadFile.do")
//	public  ResultMessage uploadPost (MultipartFile[] uploadFile) {
//		
//		String uploadFolder = "D:/Temp50/";
//		
//		for(MultipartFile multipartFile : uploadFile) {
//			
//			String uploadFileName = multipartFile.getOriginalFilename();
//			
//			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
//			
//			File saveFile = new File(uploadFolder, uploadFileName);
//			
//			try {
//				multipartFile.transferTo(saveFile);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
//		}
//		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
//	}
	
}