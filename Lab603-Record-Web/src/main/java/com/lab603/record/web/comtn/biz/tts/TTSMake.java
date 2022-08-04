package com.lab603.record.web.comtn.biz.tts;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab603.record.web.framework.utils.FrameworkUtils;

public class TTSMake
{
	public static final Logger Logger = LoggerFactory.getLogger(TTSMakeProc.class);

	public TTSMake(String prefixWavFile, String TTSServerHost, int  TTSServerPort)
	{
		this.mPrefixWavFile = prefixWavFile;
		this.mTTSServerHost = TTSServerHost;
		this.mTTSServerPort = TTSServerPort;
	}

	public String make(String text)
	{

		File   filePrefixDirectory  = new File(mPrefixWavFile);

		String strDirectoryWav		= null;
		File   fileDirectoryWav 	= null;

		String strFullPath			= null;
		File   fileFullPath			= null;


		if(FrameworkUtils.isNull(mPrefixWavFile))				  { Logger.error("PrefixWavFile Is Null"); return null; }
		if(!filePrefixDirectory.exists()) 						  { Logger.error(mPrefixWavFile + " 파일 or 디렉토리가 존재하지 않습니다.");  return null; }
		if(!filePrefixDirectory.isDirectory())					  { Logger.error(mPrefixWavFile +" `PrefixWavFile` 가 디렉토리가 아닙니다."); return null; }

		strDirectoryWav  = mPrefixWavFile + File.separator + FrameworkUtils.getCurrentDate("yyyyMMdd");
		fileDirectoryWav = new File(strDirectoryWav);

		if(!fileDirectoryWav.exists())
		{
			fileDirectoryWav.mkdirs();
		}

		strFullPath   = strDirectoryWav + File.separator + FrameworkUtils.generateSessionID() + "_" + msgSecureHashAlgorithm(text) +".wav";
		fileFullPath  = new File(strFullPath);

		try
		{
			new TTSMakeProc(this.mTTSServerHost, this.mTTSServerPort).makeWavFile(strFullPath, text);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			Logger.error("=========================================");
			Logger.error("== Error " + e.getMessage());
			Logger.error("=========================================");

			return null;
		}


		if(!fileFullPath.exists())
		{
			Logger.error(fileFullPath + " 파일 생성을 실패하였습니다.");
			return null;
		}

		return strFullPath;
	}


	private String msgSecureHashAlgorithm(String str)
	{
		String 		  rtnValue = "";
		MessageDigest sh 	   = null;

		if ( str == null) return "";

		try
		{
			sh = MessageDigest.getInstance("SHA1");

			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for(int i = 0 ; i < byteData.length ; i++)
			{
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			rtnValue = sb.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			rtnValue = null;
		}
		return rtnValue;
	}

	private String mPrefixWavFile  			= "";
	private String mTTSServerHost			= null;
	private int	   mTTSServerPort			= 0;
}

