package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.utils.PropertiesLoader;

@Controller
@RequestMapping("/interFace/payWayInfo")
public class PayWayInfoController {
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("wx.properties");
	
	private static final String PAYWAY_APPID = appPropertiesLoader.getProperty("appid");
	private static final String PAYWAY_PARTNERID = appPropertiesLoader.getProperty("partnerid");
	private static final String PAYWAY_SIGNTYPE = appPropertiesLoader.getProperty("signType");
	private static final String PAYWAY_PACKAGE = appPropertiesLoader.getProperty("package");
	private static final String PAYWAY_APIKEY = appPropertiesLoader.getProperty("apiKey");
	private static final String PAYWAY_sign = appPropertiesLoader.getProperty("sign");
	
	/**
	 * Title:获取微信支付信息
	 * @author:lishilong
	 * @date:2016年9月29日
	 */
	@RequestMapping(value = "/toWxInfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String toWxInfo(Double balance,String orderCode) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Long time = sdf.parse(sdf.format(new Date())).getTime();  
			map.put("appid", PAYWAY_APPID);
			map.put("partnerid", PAYWAY_PARTNERID);
			map.put("prepayid", "wx"+orderCode);
			map.put("noncestr", create_nonce_str());
			map.put("timestamp", time+"");
			map.put("signType", PAYWAY_SIGNTYPE);
			map.put("package", PAYWAY_PACKAGE);
			map.put("apiKey", PAYWAY_APIKEY);
			map.put("sign", PAYWAY_sign);
			map.put("balance",balance+"");
			msg.setData(map);
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			msg.setHearder(1, "error");
			e.printStackTrace();
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(map);
		return json;
	}
	
	 private static String create_nonce_str() {
		 String[] str = (UUID.randomUUID().toString()).split("-");
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<str.length;i++){
				sb.append(str[i]); 
			}
	       return sb.toString();
	 }

}
