package com.htrj.base.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htrj.common.base.BaseController;

@Controller
@RequestMapping("/a")
public class TestController extends BaseController {
	
	@ResponseBody
	@RequestMapping("/b")
	public String b(String echostr){
		if(webContext.getRequest().getMethod().toLowerCase().equals("get")){
			logger.debug("进入");
			return echostr;
		}else{
			logger.debug("推送事件");
			try{
				InputStream is;
		
				is = webContext.getRequest().getInputStream();
		
				// 已HTTP请求输入流建立一个BufferedReader对象
		
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				//http://test.sjzhtkj.com/index.php?m=Admin&c=Wechat&a=index
				// 读取HTTP请求内容
		
				String buffer = null;
				StringBuffer sb = new StringBuffer();
		
				while ((buffer = br.readLine()) != null) {
		
					// 在页面中显示读取到的请求参数
		
					sb.append(buffer);
		
				}
				logger.debug(sb.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
			logger.debug("即将return");
			return "ok";
		}
	       	
	}
}
