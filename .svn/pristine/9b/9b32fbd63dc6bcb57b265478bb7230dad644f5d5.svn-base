package com.htkj.cfdScenic.app.model;

import java.util.HashMap;
import java.util.Map;

import com.htrj.common.utils.PropertiesLoader;


public class SmsSendResult {
	
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("application.properties");
	public static final String sms_url=appPropertiesLoader.getProperty("sms.url");
	public static final String sms_batchUrl=appPropertiesLoader.getProperty("sms.batchUrl");
	public static final String sms_account=appPropertiesLoader.getProperty("sms.account");
	public static final String sms_pswd=appPropertiesLoader.getProperty("sms.pswd");
	public static final String sms_product=appPropertiesLoader.getProperty("sms.product");
	public static final String sms_extno=appPropertiesLoader.getProperty("sms.extno");
	public static final String sms_title=appPropertiesLoader.getProperty("sms.title");
	
	
	public static final Map<Integer,String> resultMap=new HashMap<Integer,String>(){{
		put(0,"提交成功");	 
		put(101,"无此用户");  
		put(102,"密码错");  
		put(103	,"提交过快（提交速度超过流速限制）");  
		put(104	,"系统忙（因平台侧原因，暂时无法处理提交的短信）");  
		put(105	,"敏感短信（短信内容包含敏感词）");  
		put(106	,"消息长度错（>536或<=0）");  
		put(107	,"包含错误的手机号码");  
		put(108	,"手机号码个数错（群发>50000或<=0;单发>200或<=0）");  
		put(109	,"无发送额度（该用户可用短信数已使用完）");  
		put(110	,"不在发送时间内");  
		put(111	,"超出该账户当月发送额度限制");  
		put(112	,"无此产品，用户没有订购该产品 ");  
		put(113	,"extno格式错（非数字或者长度不对）");  
		put(115	,"自动审核驳回");  
		put(116	,"签名不合法，未带签名（用户必须带签名的前提下）");  
		put(117	,"IP地址认证错,请求调用的IP地址不是系统登记的IP地址");  
		put(118	,"用户没有相应的发送权限");  
		put(119	,"用户已过期");  
		put(120	,"内容不在白名单模板中");  
		put(121	,"相同内容短信超限");  
	}};

}
