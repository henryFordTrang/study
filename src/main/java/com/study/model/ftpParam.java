package com.study.model;

public class ftpParam {
	private String strbase64;
	private String filename;
	
	public ftpParam() {
		super();
	}
	public ftpParam(String strbase64, String filename) {
		super();
		this.strbase64 = strbase64;
		this.filename = filename;
	}
	public String getStrbase64() {
		return strbase64;
	}
	public void setStrbase64(String strbase64) {
		this.strbase64 = strbase64;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "ftp [strbase64=" + strbase64 + ", filename=" + filename + "]";
	}
	
}
