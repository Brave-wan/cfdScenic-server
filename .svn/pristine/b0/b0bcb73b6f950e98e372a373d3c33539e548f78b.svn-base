package com.htkj.cfdScenic.app.controller;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.htkj.cfdScenic.app.util.HttpXmlUtils;
import com.htkj.cfdScenic.app.util.ParseXMLUtils;
import com.htkj.cfdScenic.app.util.RandCharsUtils;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htkj.cfdScenic.app.util.WXSignUtils;
import com.htkj.cfdScenic.app.util.WeixinConfigUtils;
import com.htkj.cfdScenic.app.util.entity.Unifiedorder;


@Controller
@RequestMapping("/interFace/WxPayController")
public class WxPayController {
	
	@RequestMapping(value="getPrePayId",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getPrePayId(String body,Integer balance,String orderCode,HttpServletRequest re,HttpServletResponse rp){
		ResponseMsg msg = new ResponseMsg();
		//参数组
		String appid = "wx4c05486c196ff0d5";
		//商户的ID
		String mch_id = "1393814102";
		//随机字符串（为了不让订单重复）
		String nonce_str = RandCharsUtils.getRandomString(16);
		//订单号
		String out_trade_no = orderCode;
		
		//留着
		String detail = "这是商品的详情？必须传？";
		String attach = "备用参数，先留着，后面会有用的";
		
		//订单金额，单位（分）
		int total_fee = balance;//单位是分
		//app实际地址IP
		String spbill_create_ip = re.getRemoteAddr();
		System.out.println(spbill_create_ip);
		//订单开始期
		String time_start = RandCharsUtils.timeStart();
		System.out.println(time_start);
		//订单结束期
		String time_expire = RandCharsUtils.timeExpire();
		//回调地址，接口端不需要回调地址
		String notify_url = "12345";
		//请求类型
		String trade_type = "APP";
		
		
		//参数：开始生成签名
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		
		//构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		Map<String,String> map = ParseXMLUtils.jdomParseXml(weixinPost);
		msg.setData(map);
		msg.setHearder(0,"ok");
		String json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		WeixinConfigUtils config = new WeixinConfigUtils();
		//参数组
//		String appid = config.appid;
		String appid = "wx4c05486c196ff0d5";
		System.out.println("appid是："+appid);
		
		String mch_id = "1393814102";
		System.out.println("mch_id是："+mch_id);
		
		String nonce_str = RandCharsUtils.getRandomString(16);
		System.out.println("随机字符串是："+nonce_str);
		String body = "1";
		String detail = "测试开始";
		String attach = "备用参数，先留着，后面会有用的";
		String out_trade_no = "2015112500001000811017342394";
		int total_fee = 1;//单位是分，即是0.01元
		String spbill_create_ip = "127.0.0.1";
		String time_start = RandCharsUtils.timeStart();
		System.out.println(time_start);
		String time_expire = RandCharsUtils.timeExpire();
		System.out.println(time_expire);
//		String notify_url = config.notify_url;
		String notify_url = "111111";
		System.out.println("notify_url是："+notify_url);
		String trade_type = "APP";
		
		
		//参数：开始生成签名
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("nonce_str", nonce_str);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);
		
		String sign = WXSignUtils.createSign("UTF-8", parameters);
		System.out.println("签名是："+sign);
		

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		
		//构造xml参数
		String xmlInfo = HttpXmlUtils.xmlInfo(unifiedorder);
		
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		String method = "POST";
		
		String weixinPost = HttpXmlUtils.httpsRequest(wxUrl, method, xmlInfo).toString();
		
		System.out.println(weixinPost);
		
		ParseXMLUtils.jdomParseXml(weixinPost);

	}
	
}
