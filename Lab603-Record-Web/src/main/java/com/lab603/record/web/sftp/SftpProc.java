package com.lab603.record.web.sftp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Hashtable;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpProc
{
	private static final Logger logger = LoggerFactory.getLogger(SftpProc.class);

	public SftpProc(String strRemoteHost, int iRemotePort, String strRemoteID, String strRemotePassword)
	{
		this.mRemoteHost 		= strRemoteHost;
		this.mRemotePort 		= iRemotePort;
		this.mRemoteID 			= strRemoteID;
		this.mRemotePassword 	= strRemotePassword;
	}


    public boolean Get(String remotePath, String remoteFile, String lPath)
    {
        connect();
        sftpRemotely();

        try
        {
            mChannelSftp.cd(remotePath);
            mChannelSftp.get(remoteFile, lPath);

            clear();
        }
        catch (SftpException e)
        {
            e.printStackTrace();
            logger.error("#####################################");
            logger.error("#####"+e.getMessage());
            logger.error("#####################################");
        }
        finally
        {
            clear();
        }
        return true;
    }

    private void connect()
    {
        try
        {
        	logger.debug("mRemoteID : " + mRemoteID);
        	logger.debug("mRemoteHost : " + mRemoteHost);
        	logger.debug("mRemotePort : " + mRemotePort);
        	logger.debug("mRemotePassword : " + mRemotePassword);

            mJSch = new JSch();
            mSession = mJSch.getSession(mRemoteID, mRemoteHost, mRemotePort);
            mSession.setPassword(mRemotePassword);
            mSession.setConfig(mSessionConfig);
            mSession.connect();
        }
        catch (JSchException e)
        {
            e.printStackTrace();
            logger.error("#####################################");
            logger.error("#####"+e.getMessage());
            logger.error("#####################################");
        }
    }

    private void sftpRemotely()
    {
        try
        {
            mChannelSftp = (ChannelSftp) mSession.openChannel("sftp");
            mChannelSftp.connect();
        }
        catch (JSchException e)
        {
            e.printStackTrace();
            logger.error("#####################################");
            logger.error("#####"+e.getMessage());
            logger.error("#####################################");
        }
    }

    private void clear()
    {
        mSession.disconnect();
        mChannelSftp.disconnect();

        mJSch                   = null;

        logger.info("                      Ok!");
    }

    private final static Hashtable<String, String> mSessionConfig;
    static
    {
        mSessionConfig  = new Hashtable<String, String>();
        mSessionConfig.put("StrictHostKeyChecking", "no");
    }

    private JSch        mJSch                           = null;
    private Session     mSession                        = null;
    private ChannelSftp mChannelSftp                    = null;


    private String mRemoteHost                = null;
    private int    mRemotePort                = 0;
    private String mRemoteID                  = null;
    private String mRemotePassword            = null;
}
