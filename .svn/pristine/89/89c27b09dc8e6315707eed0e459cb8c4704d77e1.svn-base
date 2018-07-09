package com.htrj.common.remotefile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * 远程文件实体
 * @author Administrator
 *
 */
public class RemoteFile {
	private String fileName;//文件名称
	private Double fileSize;//文件大小 （mb）
	private String fileType;//文件类型
	private InputStream fileInputStream;//文件输入流
	private String md5Code;
	private byte[] byteArray;
	
	public RemoteFile(){}
	
	public RemoteFile(String fileName, Double fileSize, String fileType, InputStream fileInputStream){
		this.fileName=fileName;
		this.fileSize=fileSize;
		this.fileType=fileType;
		this.fileInputStream=fileInputStream;
	}
	
	/**
	 * 获取远程文件的MD5码,获取该码之前，应先调用creatFileMD5Code()方法生成MD5码，否则将返回空值
	 * @param remoteFile
	 * @return
	 */
	public String getFileMD5Code(){
        return md5Code;
	}
	
	/**
	 * 将单个远程文件保存到本地
	 * @param path	本地路径,以/结尾
	 * @param fileNewName	文件的保存名称（新名）
	 * @return
	 */
	public boolean saveFileToLocal(String path,String fileNewName){
		String fileUrl=path+fileNewName+"."+this.getFileType();
		File uploadFile=new File(fileUrl);
		try {
			FileOutputStream fs = new FileOutputStream(uploadFile);
			fs.write(byteArray);
			fs.flush();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean saveFileToLocalByStream(String path,String fileNewName){
		String fileUrl=path+fileNewName+"."+this.getFileType();
		File uploadFile=new File(fileUrl);
		try {
			FileOutputStream fs = new FileOutputStream(uploadFile);
			InputStream stream = this.getFileInputStream();
			byte[] buffer = new byte[1024 * 1024];
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 创建该远程文件的md5码（一旦创建md5码，该对象的输入流将作废，如果想要对原输入流中的数据进行操作，必须使用本对象提供的保存方法，或者通过本对象获取原输入流的字节数组进行操作）
	 * ！！！！使用完毕请及时销毁该对象，避免占用内存！！！！！
	 * @return
	 */
	public boolean creatFileMD5Code(){
		try {
			byteArray=IOUtils.toByteArray(this.getFileInputStream());
			IOUtils.closeQuietly(this.getFileInputStream());
        	md5Code = DigestUtils.md5Hex(byteArray);
        	this.getFileInputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
        return true;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public void closeInputStream() throws IOException{
		this.fileInputStream.close();
	}
	
}
