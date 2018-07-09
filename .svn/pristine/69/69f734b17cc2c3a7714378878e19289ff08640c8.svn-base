package com.htkj.cfdScenic.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.service.PuchMessageService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;

@RequestMapping("pushMessage")
@Controller
public class PushMessageController extends BaseController{
	/**
	 * 张腾跃
	 * 2016年10月12日14:23:32
	 * PC商户消息中心
	 */
	@Autowired
	private ShopInformationService shopInformationService;
	@Autowired
	private PuchMessageService puchMessageService;
	
	@RequestMapping("tonews")
	public String tonews(){
		return "/background/news/Manager";
	}
	@RequestMapping("news")
	@ResponseBody
	public DataGrid news(PagerForm page,PushMessage pushMessage){
		ShopUser user = (ShopUser) webContext.getSessionShopUser();
		pushMessage.setUserId(user.getId());
		DataGrid list = puchMessageService.pressPCList(page.getPageRequest(pushMessage.toMap()));
		return list;
	}
}
