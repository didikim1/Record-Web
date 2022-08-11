package com.lab603.record.web.comtn.biz;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lab603.record.web.comtn.biz.dto.TTSWavDTO;
import com.lab603.record.web.comtn.biz.tts.TTSMake;
import com.lab603.record.web.framework.mymap.MyMap;
import com.lab603.record.web.mapper.comtn.ttsServerInfo.TTSServerInfoMapper;

@Service("com.lab603.record.web.comtn.biz.TTSServerInfoBiz")
public class TTSServerInfoBiz
{
	public static final Logger Logger = LoggerFactory.getLogger(TTSServerInfoBiz.class);

	@Value("${TTS.Wav.File.Prefix}")
    private String TTS_Wav_File_Prefix;
	
	@Value("${TTS.Wav.RealFile.Prefix}")
	private String TTS_Wav_RealFile_Prefix;

	@Resource(name="com.lab603.record.web.mapper.comtn.ttsServerInfo.TTSServerInfoMapper")
	TTSServerInfoMapper mMapper;

	@Resource(name="com.lab603.record.web.comtn.biz.TTSServerInfoBiz")
	TTSServerInfoBiz mBiz;

	public TTSWavDTO ttsMake(String text)
	{
		System.out.println("TTS_Wav_File_Prefix : " + TTS_Wav_File_Prefix);

		MyMap	    ttsServerInfo 	= null;
		TTSWavDTO 	dto 			= null;
		String		filePath	    = null;

		ttsServerInfo = this.SelectOneData(new MyMap());
		if( ttsServerInfo == null ) { Logger.error("TTS 서버가 등록되지 않았습니다."); return null; }


		TTSMake proc = new TTSMake(TTS_Wav_File_Prefix, ttsServerInfo.getStr("ip"), ttsServerInfo.getInt("port"));
		filePath = proc.make(text);

		if( filePath != null )
		{
			dto = new TTSWavDTO();
			dto.setFilepath(filePath);
			dto.setFilesize(new File(filePath).length());
		}

		return dto;
	}
	
	public TTSWavDTO ttsRealMake(String text)
	{
		System.out.println("TTS_Wav_RealFile_Prefix : " + TTS_Wav_RealFile_Prefix);
		
		MyMap	    ttsServerInfo 	= null;
		TTSWavDTO 	dto 			= null;
		String		filePath	    = null;
		
		ttsServerInfo = this.SelectOneData(new MyMap());
		if( ttsServerInfo == null ) { Logger.error("TTS 서버가 등록되지 않았습니다."); return null; }
		
		
		TTSMake proc = new TTSMake(TTS_Wav_RealFile_Prefix, ttsServerInfo.getStr("ip"), ttsServerInfo.getInt("port"));
		filePath = proc.make(text);
		
		if( filePath != null )
		{
			dto = new TTSWavDTO();
			dto.setFilepath(filePath);
			dto.setFilesize(new File(filePath).length());
		}
		
		return dto;
	}



	public MyMap SelectOneData(MyMap paramMap)
	{
		List<MyMap> result = mMapper.SelectOneData(paramMap);

		if( result == null ) 		return null;
		if( result.size() == 0 ) 	return null;

		return result.get(0);
	}

	
	public TTSWavDTO uploadFile(MultipartFile file) {
		System.out.println("TTS_Wav_File_Prefix : " + TTS_Wav_File_Prefix);
		
		MyMap	    ttsServerInfo 	= null;
		TTSWavDTO 	dto 			= null;
		String		filePath	    = null;
		
		ttsServerInfo = this.SelectOneData(new MyMap());
		if( ttsServerInfo == null ) { Logger.error("TTS 서버가 등록되지 않았습니다."); return null; }
		
		TTSMake proc = new TTSMake(TTS_Wav_File_Prefix, ttsServerInfo.getStr("ip"), ttsServerInfo.getInt("port"));
		
		if( filePath != null )
		{
			dto = new TTSWavDTO();
			dto.setFilepath(filePath);
			dto.setFilesize(new File(filePath).length());
		}
		
		return dto;
	}
	
}
