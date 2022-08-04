package com.lab603.record.web.comtn.biz.dto;

public class TTSWavDTO
{
	private String filepath;
	private long   filesize;

	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "TTSWavDTO [filepath=" + filepath + ", filesize=" + filesize + "]";
	}


}
