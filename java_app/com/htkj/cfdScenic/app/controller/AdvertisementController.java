package com.htkj.cfdScenic.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.service.AdvertisementService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController extends BaseController{
	@Autowired
	private AdvertisementService advertisementService;
	
	/*
	 * 景区-首页轮播图
	 * GET
	 * http://localhost:8080/cfdScenic/advertisement/carouselImg
	 * 把表内轮播的图片返回link_id(链接id)img_url(图片url地址)title(名称)advert_describe(描述)
	 * 调用表 advertisement
	 */
	@RequestMapping(value="/carouselImg",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String carouselImg(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			
			list = advertisementService.selectImgUrl();
			if(list.size()>0&&list.get(0)!=null)
			{
				msg.setData(list);
			}
			else
			{
				msg.setData(new ArrayList());
			}
			msg.setHearder(0, "success");
			
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/*
	 * 观鸟，湿地，招商-结伴游显示
	 * GET
	 * 调用表 advertisement
	 */
	@RequestMapping(value="/getCarouselImg",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getCarouselImg(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			
			list = advertisementService.getCarouselImg();
			if(list.size()>0&&list.get(0)!=null)
			{
				msg.setData(list);
			}
			else
			{
				msg.setData(new ArrayList());
			}
			msg.setHearder(0, "success");
			
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	

}
