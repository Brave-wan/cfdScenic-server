package com.htkj.cfdScenic.app.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcloud.msg.http.HttpSender;
import com.htkj.cfdScenic.app.model.SmsSendRecord;
import com.htkj.cfdScenic.app.model.SmsSendResult;
@Service
@Transactional
public class SmsSendService {
	
	

	public Map<String,String> SmsSend(SmsSendRecord smsSendRecord) throws UnsupportedEncodingException
	{
	
		Map<String,String> resultMap=new HashMap<String,String>();
		
		String uri = "";//应用地址
		String account =SmsSendResult.sms_account;//账号
		String pswd = SmsSendResult.sms_pswd;//密码
		String mobiles =new String(smsSendRecord.getMobiles().getBytes(),"UTF-8");//手机号码，多个号码使用","分割
//		String content = smsSendRecord.;//短信内容
		boolean needstatus = true;//是否需要状态报告，需要true，不需要false
		String product =SmsSendResult.sms_product;//产品ID
		String extno = SmsSendResult.sms_extno;//扩展码
		String msg=   new String(("【曹妃甸】"+smsSendRecord.getContent().trim()).getBytes("UTF-8"),"UTF-8");
		
		try {
			
			String returnString ="";
			if(smsSendRecord.isBatch())
			{
				uri=SmsSendResult.sms_batchUrl;
				returnString = HttpSender.batchSend(uri, account, pswd, mobiles, msg, needstatus, product, extno);
			}else
			{
				uri=SmsSendResult.sms_url;
				returnString = HttpSender.send(uri, account, pswd, mobiles, msg, needstatus, product, extno);
			}
			System.out.print(returnString);
			returnString=returnString.replaceAll("\r|\n", ",");
			int num=Integer.parseInt(returnString.split(",")[1].toString());
			
			resultMap.put("state",num+"");
			
			resultMap.put("data", SmsSendResult.resultMap.get(num));
			
			System.out.println(returnString);
		} catch (Exception e) {

			e.printStackTrace();
		}
	
		
		return resultMap;
		
		
	}

}
