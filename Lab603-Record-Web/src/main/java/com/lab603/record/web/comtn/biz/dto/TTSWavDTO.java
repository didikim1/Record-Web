package com.lab603.record.web.comtn.biz.dto;

public class TTSWavDTO
{
	private String filepath;
	private long   filesize;
	private String webLink;

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
	public String getWebLink() {
		return webLink;
	}
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}
	@Override
	public String toString() {
		return "TTSWavDTO [filepath=" + filepath + ", filesize=" + filesize + ", webLink=" + webLink + "]";
	}
}
