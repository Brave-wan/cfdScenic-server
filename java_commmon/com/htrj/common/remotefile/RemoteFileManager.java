package com.htrj.common.remotefile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 对远程文件进行操作
 * @author Administrator
 *
 */
public class RemoteFileManager {
	
	private MultipartHttpServletRequest request;
	private List<RemoteFile> fileList;
	private Map<String, RemoteFile> remoteFileMap;
	public RemoteFileManager(MultipartHttpServletRequest request){
		this.request=request;
		this.creatRemoteFiles(this.request);
	}
	
	/**
	 * 拼装请求中所有文件的远程文件对象
	 * @param request
	 */
	public void creatRemoteFiles(MultipartHttpServletRequest request){
		Map<String, MultipartFile> fileMap = request.getFileMap();
		fileList=new ArrayList();
		remoteFileMap=new HashMap<String, RemoteFile>();
		if (fileMap.isEmpty() || fileMap == null){
			return;
		}
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			String fileName=mf.getOriginalFilename();
			if (fileName == "" || fileName == null)
				continue;
			RemoteFile remoteFile=new RemoteFile();
			
			try {
				remoteFile.setFileInputStream(mf.getInputStream());
			} catch (IOException e) {
				System.out.println(e.getMessage());
				continue;
			}
			Integer lastDelIndex=fileName.lastIndexOf(".");
			remoteFile.setFileName(fileName.substring(0, lastDelIndex));
			remoteFile.setFileSize((double)(mf.getSize()/1024));
			remoteFile.setFileType(fileName.substring(lastDelIndex+1, fileName.length()));
			fileList.add(remoteFile);
			remoteFileMap.put(remoteFile.getFileName(), remoteFile);
		}
	}
	
	/**
	 * 通过文件名称获取远程文件对象
	 * @param name	文件名称
	 * @return
	 */
	public RemoteFile getRemoteFileByName(String name){
		return remoteFileMap.get(name);
	}

	public List<RemoteFile> getFileList() {
		return fileList;
	}
	
}
