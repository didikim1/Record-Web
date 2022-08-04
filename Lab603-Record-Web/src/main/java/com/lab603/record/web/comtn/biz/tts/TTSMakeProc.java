package com.lab603.record.web.comtn.biz.tts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import voiceware.libttsapi;

public class TTSMakeProc
{

	public static final Logger Logger = LoggerFactory.getLogger(TTSMakeProc.class);

	final static int DEF_SPEED_TAG  = 115;

	public TTSMakeProc(String host, int port)
	{
		this.m_strServerIP =  host;
		this.m_iServerPort =  port;
	}

	public boolean makeWavFile( String strWaveFileName, String strMessage ) throws IOException
	{
		boolean isFileMakeFlag =  false;

		int nRet = libttsapi.TTS_RESULT_SUCCESS;
		libttsapi ttsapi = new libttsapi();

		byte[] bzText = strMessage.getBytes("EUC-KR");

		nRet = ttsapi.ttsRequestBuffer(m_strServerIP, m_iServerPort, bzText, libttsapi.TTS_HYERYUN_DB,
				libttsapi.FORMAT_WAV, libttsapi.TRUE, 1);

		if (nRet == libttsapi.TTS_RESULT_SUCCESS)
		{
			File f = new File(strWaveFileName);
			FileOutputStream os = new FileOutputStream(f);

			os.write(ttsapi.szVoiceData, 0, ttsapi.nVoiceLength);
			os.close();

			if( f.exists() )
			{
				isFileMakeFlag = true;
			}
		}

		return isFileMakeFlag;
	}

	private String m_strServerIP = null;
	private int    m_iServerPort = 0;
}
