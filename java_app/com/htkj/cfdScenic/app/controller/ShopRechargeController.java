package com.htkj.cfdScenic.app.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.SmsSendRecord;
import com.htkj.cfdScenic.app.model.SysVerification;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.service.AdvertisementService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopGoodsService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.SmsSendService;
import com.htkj.cfdScenic.app.service.SysVerificationService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/recharge")
public class ShopRechargeController extends BaseController{

	@Autowired
	private ShopInformationService consumerUserService;
	
	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private UserAccountService userAccountService;

	
	/*
	 * 查询用户信息
	 * post
	 * http://localhost:8080/cfdScenic/recharge/findUserMessage
	 * 返回状态信息
	
	 */
	@RequestMapping(value = "findUserMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findUserMessage(String telphone) {
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		if(token!=null){
			if(userId!=null){
				
				Map<String,Object> userMap = consumerUserService.findUserMessage(telphone);
				msg.setHearder(0, "success");
				msg.setData(userMap);
	
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		String json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/*
	 * 充值
	 * post
	 * http://localhost:8080/cfdScenic/recharge/saveRecharge
	 * consumerId 用id    balance 充值金额   userBalance 用户剩余
	 * 返回状态信息
	
	 */
	@RequestMapping(value = "saveRecharge", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findUserMessage(Long consumerId,Double balance,Double userBalance) {
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		if(token!=null){
			if(userId!=null){
				map.put("consumerId", consumerId);
				map.put("balance", balance);

				
				UserAccount userAccount = userAccountService.findByUserId(userId);
				if(userAccount.getBalance().compareTo(balance)==1)
				{
					userAccount.setBalance(userAccount.getBalance()-balance);
					userAccountService.updateBalanceMessage(userAccount);
					
					userAccount.setUserId(consumerId);
					userAccount.setBalance(userBalance+balance);
					
					userAccountService.updateBalanceMessage(userAccount);
					msg.setHearder(0, "success");
				}else
				{
					msg.setHearder(4, "商户余额不足，请先充值");
				}
				
	
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		String json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
}
