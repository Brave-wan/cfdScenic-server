package com.htrj.common.download;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class ResponseSetter {
	public static final String TYPE_PDF="application/pdf";
	public static final String TYPE_AFP="application/afp";
	public static final String TYPE_RTF="application/rtf";
	public static final String TYPE_MSWORD="application/msword";
	public static final String TYPE_MSEXCEL="application/vnd.ms-excel";
	public static final String TYPE_MSPOWERPOINT="application/vnd.ms-powerpoint";
	public static final String TYPE_WORDPERFECT="application/wordperfect5.1";
	public static final String TYPE_WORDPRO="application/vnd.lotus-wordpro";
	public static final String TYPE_VISIO="application/vnd.visio";
	public static final String TYPE_FRAMEMAKER="application/vnd.framemaker";
	public static final String TYPE_LOTUS123="application/vnd.lotus-1-2-3";
	public static final String TYPE_XML="text/xml";
	public static final String TYPE_TXT="text/plain";
	public static final String TYPE_HTML="text/html";
	public static final String TYPE_JSON="text/javascript";
	public static final String TYPE_PCX="image/x-pcx";
	public static final String TYPE_DCX="image/x-dcx";
	public static final String TYPE_TIFF="image/tiff";
	public static final String TYPE_JPEG="image/jpeg";
	public static final String TYPE_GIF="image/gif";
	public static final String TYPE_BMP="image/bmp";
	
	public static final String CHARSET_UTF8="UTF-8";
	public static final String CHARSET_GBK="GBK";
	
	public static void setContentType(HttpServletResponse response,String contentType,String charsetEncoding){
		response.setContentType(contentType);
		if(charsetEncoding!=null)
			response.setCharacterEncoding(charsetEncoding);
	}
	
	public static void addOutputStream(HttpServletResponse response,String filePath){
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(filePath);
			int len = 0;
			byte[] buffer = new byte[1024 * 1024];
			out = response.getOutputStream();
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
