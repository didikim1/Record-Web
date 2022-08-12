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

import org.jxls.common.Size;
import org.neo4j.cypher.internal.compiler.v1_9.parser.ParsedNamedPath;
import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.internalDocBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lab603.record.web.comtn.biz.ServerInfoBiz;
import com.lab603.record.web.comtn.biz.TTSServerInfoBiz;
import com.lab603.record.web.comtn.biz.dto.TTSWavDTO;
import com.lab603.record.web.framework.beans.BasicBean;
import com.lab603.record.web.framework.beans.FrameworkBeans;
import com.lab603.record.web.framework.mymap.MyCamelMap;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.framework.result.ResultCode;
import com.lab603.record.web.framework.result.ResultMessage;
import com.lab603.record.web.framework.utils.FrameworkUtils;
import com.lab603.record.web.makeTTS.biz.MakeTTSBiz;
import com.lab603.record.web.trunk.biz.TrunkBiz;

import ch.qos.logback.classic.pattern.FileOfCallerConverter;
import ch.qos.logback.core.util.FileSize;
import scala.reflect.internal.Trees.New;


@Controller
@RequestMapping("/makeTTS")
public class MakeTTSAct
{
	final String pagePrefix = "makeTTS";

	@Value("${TTS.Wav.TempFile.Prefix}")
    private String TTS_Wav_TempFile_Prefix;

	@Value("${TTS.Wav.RealFile.Prefix}")
	private String TTS_Wav_RealFile_Prefix;

	@Value("${TTS.Wav.UploadFile.Prefix}")
	private String TTS_Wav_UploadFile_Prefix;

	@Value("${TTS.Wav.File.CallTTSWav.Prefix}")
	private String TTS_Wav_File_CallTTSWav_Prefix;

	@Value("${TTS.Wav.File.WebLink.RealFile.Prefix}")
	private String TTS_Wav_File_WebLink_RealFile_Prefix;

	@Value("${TTS.Wav.File.WebLink.TempFile.Prefix}")
	private String TTS_Wav_File_WebLink_TempFile_Prefix;

	private static final Logger logger = LoggerFactory.getLogger(MakeTTSAct.class);

	@Resource(name="com.lab603.record.web.makeTTS.biz.MakeTTSBiz")
	MakeTTSBiz mBiz;

	@Resource(name="com.lab603.record.web.comtn.biz.TTSServerInfoBiz")
	TTSServerInfoBiz TTSServerInfoBizmBiz;

	@Resource(name="com.lab603.record.web.comtn.biz.ServerInfoBiz")
	ServerInfoBiz mServerInfoBiz;

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


	/**
	 * TTS생성(임시파일)
	 */
	@RequestMapping(value = {"/makeFile.do" })
	public @ResponseBody ResultMessage MakeFile(Model model)
	{
		MyMap 	    paramMap 				  	= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 					= new MyCamelMap();
		String      ttsMent 					= paramMap.getStr("ttsMent");
		String 		ip							= mServerInfoBiz.SelectOneData(new MyMap()).getStr("ip");
		String		filePath					= null;
		String		ttsWebLink					= TTS_Wav_File_WebLink_TempFile_Prefix.toString();

		TTSWavDTO dto = TTSServerInfoBizmBiz.ttsMake(ttsMent);

		filePath   = dto.getFilepath().toString();

		ttsWebLink  += filePath.replace(TTS_Wav_TempFile_Prefix, "")+new File(dto.getFilepath()).getName();
		ttsWebLink	=  ttsWebLink.replaceAll("\\{ip}",  ip);

		System.out.println(dto.toString());

		dto.setWebLink(ttsWebLink);

		resultMap.put("result", dto);

		return new ResultMessage(ResultCode.RESULT_OK, resultMap);
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

		model.addAttribute("paramMap",      paramMap);
		model.addAttribute("Info",          resultMap);

		return pagePrefix + "/RegisterContent";
	}

	/**
	 *  TTS 등록 (DB저장)
	 */
	@RequestMapping(value = { "/RegisterData.do" })
	public @ResponseBody ResultMessage RegisterData(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;

		String filepath 		= paramMap.getStr("filepath");
		String tts      		= paramMap.getStr("tts");
		String filesize         = paramMap.getStr("filesize");

		File   temp_file 		= null;

		String savePath			= TTS_Wav_RealFile_Prefix.toString();
		String callTTSWav       = TTS_Wav_File_CallTTSWav_Prefix.toString();
		String webLink			= TTS_Wav_File_WebLink_RealFile_Prefix.toString();
		String ip				= mServerInfoBiz.SelectOneData(new MyMap()).getStr("ip");

		savePath += File.separator + FrameworkUtils.getCurrentDate("yyyyMMdd") + File.separator;

		if( !new File(savePath).exists() ) { new File(savePath).mkdirs(); }

		System.out.println("savePath : " + filepath);

		temp_file = new File(filepath);

		boolean success = temp_file.renameTo(new File(savePath + temp_file.getName()));

		if (!success) {
		    System.out.println("Failed to rename"+savePath + temp_file.getName());

		    return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, resultMap);
		}

		callTTSWav += ( savePath.replace(TTS_Wav_RealFile_Prefix, "") + temp_file.getName() );
		callTTSWav = callTTSWav.replaceAll(".wav", "");

		webLink	   += ( savePath.replace(TTS_Wav_RealFile_Prefix, "") + temp_file.getName() );
		webLink	   =  webLink.replaceAll("\\{ip}",  ip);

		paramMap.put("ttsMent", 	tts);
		paramMap.put("ivrPath", 	savePath + temp_file.getName());
		paramMap.put("callTTSWav",  callTTSWav);
		paramMap.put("webLink",  	webLink);

		System.out.println("===========================");
		System.out.println(paramMap.toString());
		System.out.println("===========================");


		resultRegisterDataCount = mBiz.RegisterData( paramMap );

		resultMap.put("ResultDataCount", resultRegisterDataCount);
		resultMap.put("result", paramMap);

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
	public @ResponseBody ResultMessage uploadFile ( HttpServletRequest request, @RequestParam("fileName") MultipartFile file, @RequestParam String ttsTitle)
	{
		File target 	 	= null;

		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();

		String fileDirectory = TTS_Wav_RealFile_Prefix + File.separator + FrameworkUtils.getCurrentDate("yyyyMMdd");
		String fileName 	 = "UPLOAD_"+FrameworkUtils.generateSessionID()+".wav";

		String fileFullPath  = fileDirectory + File.separator + fileName;
		String callTTSWav    = TTS_Wav_File_CallTTSWav_Prefix.toString();
		String webLink		 = TTS_Wav_File_WebLink_RealFile_Prefix.toString();
		String ip			 = mServerInfoBiz.SelectOneData(new MyMap()).getStr("ip");

		int	   iRtn 		 = 0;

		MyMap  registerMap	 = new MyMap();

		if( !new File(fileDirectory).exists() )
		{
			new File(fileDirectory).mkdirs();
		}

		target = new File(fileDirectory, fileName);

		try
		{
			FileCopyUtils.copy(file.getBytes(), target);

			callTTSWav += ( fileFullPath.replace(TTS_Wav_RealFile_Prefix, "") + fileName );
			callTTSWav = callTTSWav.replaceAll(".wav", "");

			webLink	   += ( fileFullPath.replace(TTS_Wav_RealFile_Prefix, "") + fileName );
			webLink	   =  webLink.replaceAll("\\{ip}",  ip);

			// ttsTitle

			registerMap.put("ttsTitle",    paramMap.getStr("ttsTitle"));
			registerMap.put("ttsMent", 	   "");
			registerMap.put("ivrPath", 	   fileFullPath);
			registerMap.put("callTTSWav",  callTTSWav);
			registerMap.put("webLink",     webLink);

			iRtn = mBiz.RegisterData( registerMap );
		}
		catch (Exception e)
		{
			e.printStackTrace();

			return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
		}

		if( iRtn > 0 )
		{
			 return new ResultMessage(ResultCode.RESULT_OK, registerMap);
		}
		return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);

		/*
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		MyCamelMap 	resultMap 				= new MyCamelMap();
		int			resultRegisterDataCount = 0;

//		String saveDir = request.getSession().getServletContext().getRealPath("D:/Temp50/upload"+orifileName);
		String Dir = TTS_Wav_UploadFile_Prefix;

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
        */


       // return new ResultMessage(ResultCode.RESULT_OK, null);
   }



	/**
	 * 임시저장 tts 파일삭제
	 */
	@RequestMapping(value = {"/DelectFile.do" })
	public @ResponseBody ResultMessage DelectFile(Model model)
	{
		MyMap 		paramMap 				= FrameworkBeans.findHttpServletBean().findClientRequestParameter();
		/*
		MyCamelMap 	resultMap 				= new MyCamelMap();

		File rootDir 	= new File(TTS_Wav_File_Prefix);

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
	      */

		String filepath = paramMap.getStr("filepath");

		System.out.println("filepath = " + filepath);


		if( new File(filepath).delete() )
		{
			return new ResultMessage(ResultCode.RESULT_OK, null);
		}
		return new ResultMessage(ResultCode.RESULT_INTERNAL_SERVER_ERROR, null);
	}

}