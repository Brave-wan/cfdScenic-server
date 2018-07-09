package com.htkj.cfdScenic.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/share")
@Controller
public class ShareController {
	
	@RequestMapping("toShare")
	public String toVideoShare(String headImg,String name,String date,String title,String content,String pic,String video,Integer shareType,ModelMap model){
		String url = "";
		try {
			model.put("headImg",headImg);
			model.put("name",name);
			model.put("title",title);
			model.put("content",content);
			model.put("date",date);
			if(shareType == 0){
				List<String> list = new ArrayList<String>();
				if(pic.indexOf(",") != -1){
					String[] str = pic.split(","); 
					for(int i=0;i<str.length;i++){
						list.add(str[i]);
					}
				}else{
					list.add(pic);
				}
				model.put("pic",list);
				url = "/background/share/sharePic";
			}else{
				model.put("video",video);
				url = "/background/share/shareVideo";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	@RequestMapping("toDownload")
	public String toDownload(){
		String url = "";
		try {
				url = "/background/download/downLoad";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
