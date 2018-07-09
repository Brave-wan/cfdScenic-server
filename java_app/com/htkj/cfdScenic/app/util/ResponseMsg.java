package com.htkj.cfdScenic.app.util;

public class ResponseMsg {
	private Header header;
	private Object data;
	
	public ResponseMsg(){
		header = new Header();
	}
	
	public void setHearder(int status, String msg){
		header.setStatus(status);
		header.setMsg(msg);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
}