package com.htrj.common.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.htrj.common.base.BaseController;

@Controller
@RequestMapping("/testFile")
public class UpAndDownFileController  extends BaseController {
	
	@ResponseBody
	@RequestMapping("/getdoc")
	public void getdoc(HttpServletRequest request,
			HttpServletResponse response) {
		
		String filename = request.getSession().getServletContext().getRealPath("/")+"upload/123.doc";
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(filename));
			java.io.OutputStream outStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int bytes = 0;
			while ((bytes = in.read(buf)) != -1)
				outStream.write(buf, 0, bytes);
			in.close();
			outStream.close();
		} catch (Exception e){
			logger.error("系统错误：", e);
		}
	}
	
	@ResponseBody
	@RequestMapping("/savedoc")
	public void savedoc(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		
		String filename = request.getSession().getServletContext().getRealPath("/")+"upload/123.doc";
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		if (fileMap.isEmpty() || fileMap == null){
			response.getWriter().write("failed");
			return;
		}
		SimpleDateFormat from = new SimpleDateFormat("yyyyMMddHHmm");
		String time = from.format(new Date());
		String uploadPath = "upload/" + time + "/";
//		File f1 = new File(rootPath + "/" + uploadPath);
		File f1 = new File(filename);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		MultipartFile mf = null;
		for(String key:fileMap.keySet()){
			logger.debug("fileMap.get(key) the key is {}",key);
			mf = fileMap.get(key);
		}
//		MultipartFile mf = fileMap.get("");
		
		String OriginalFilename = mf.getOriginalFilename();
		if (OriginalFilename == "" || OriginalFilename == null){
			response.getWriter().write("failed");
			return;
		}
		
		Long fileSize = mf.getSize();
		String fileExt = OriginalFilename.substring(
				OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Integer r;
			r = new Random().nextInt(1000);
			
		String code = df.format(new Date()) + "_" + r;
		String newFileName = code + "." + fileExt;
		String fileurl = uploadPath + newFileName;
		File uploadFile = new File(rootPath + "/" + uploadPath
				+ newFileName);
		try {
//			FileOutputStream fs = new FileOutputStream(uploadFile);
			FileOutputStream fs = new FileOutputStream(filename);
			InputStream stream = mf.getInputStream();
			byte[] buffer = new byte[1024 * 1024];
			int bytesum = 0;
			int byteread = 0;
			while ((byteread = stream.read(buffer)) != -1) {
				bytesum += byteread;
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.getWriter().write("succeed");
	}	
}
