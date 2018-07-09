package com.htrj.common.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.htkj.cfdScenic.app.model.ImageBean;
import com.htkj.cfdScenic.app.util.WxGlobal;
import com.htrj.common.utils.CalendarHelper;
public class UploadUtil {
	
	public static String getImagePath(){
		SimpleDateFormat from = new SimpleDateFormat("yyyyMM");
		String time = from.format(new Date());
		
		String imagePath = WxGlobal.imagePath + time + "\\";
		
		File f1 = new File(imagePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		return imagePath;
	}
	
	/**
	 * 获取文件上传后的存储位置和访问图片时传入的名字
	 * @param extention
	 * @return
	 */
	public static ImageBean getImagePath(String extention){
		SimpleDateFormat from = new SimpleDateFormat("yyyyMM");
		String time = from.format(new Date());
		
		String imagePath = WxGlobal.imagePath + time + "\\";
		
		File f1 = new File(imagePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		String fileName = System.currentTimeMillis() + extention;
		ImageBean bean = new ImageBean(imagePath + fileName, time + "\\" + fileName);
		return bean;
	}
	
}
