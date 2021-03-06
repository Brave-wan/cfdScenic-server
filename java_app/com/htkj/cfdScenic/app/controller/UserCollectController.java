package com.htkj.cfdScenic.app.controller;

import java.io.UnsupportedEncodingException;
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
import com.htkj.cfdScenic.app.model.UserCollect;
import com.htkj.cfdScenic.app.service.AdvertisementService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopGoodsService;
import com.htkj.cfdScenic.app.service.SmsSendService;
import com.htkj.cfdScenic.app.service.SysVerificationService;
import com.htkj.cfdScenic.app.service.UserCollectService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/interFace/collect")
public class UserCollectController extends BaseController{
	
	
	
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private UserCollectService userCollectService;
	
	
	
	/**
	 * 商城 - 收藏
	 * http://localhost/cfdScenic/interFace/collect/save?id=1
	 * 流程 1,分页查询所有的饭店 调用表 shop_information
	 */
	@RequestMapping(value = "/save", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String collect(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					if(id!=null)
					{
						
						UserCollect collect=new UserCollect();
						collect.setId(GenerateSequenceUtil.getUniqueId());
						Integer type=0;
						if(type!=null)
						{
							if(type==0)
							{
								collect.setType(0);
								collect.setGoodsId(id);
								collect.setUserId(userId);
								collect.setState(0);
							}else
							{
								collect.setType(1);
								collect.setShopId(id);
								collect.setUserId(userId);
								collect.setState(0);
							}
						} 
						Integer num =userCollectService.queryCollect(collect);
						if(num==0)
						{
							userCollectService.insertCollect(collect);
							msg.setHearder(0, "ok");
						}else
						{
							msg.setHearder(5, "该商品已经收藏");
						}
						
					}else
					{
						msg.setHearder(4, "收藏参数不能为空 ");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	/**
	 * 我的 - 收藏删除
	 * http://localhost:8080/cfdScenic/interFace/collect/delete?id=1
	 * 流程 1,分页查询所有的饭店 调用表 shop_information
	 */
	@RequestMapping(value = "/delete", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(String ids) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					if(ids!=null)
					{
						Map map = new HashMap();
						map.put("id",ids);
						userCollectService.deleteCollect(map);
						msg.setHearder(0, "ok");
					}else
					{
						msg.setHearder(4, "参数为空");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 商品详情中根据用户和商品ID取消收藏接口
	 * http://localhost:8080/cfdScenic/interFace/eShop/collect?id=1
	 * 流程 1,分页查询所有的饭店 调用表 shop_information
	 */
	@RequestMapping(value = "/deleteByGoodsId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteByGoodsId(Long goodsId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					if(goodsId!=null)
					{
						Map param = new HashMap();
						param.put("uid", userId);
						param.put("gid", goodsId);
						userCollectService.delCollectByGoodsAndUid(param);
						msg.setHearder(0, "ok");
					}else
					{
						msg.setHearder(4, "参数为空");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/**
	 * 我的 - 收藏查询
	 * http://localhost:8080/cfdScenic/interFace/eShop/select?page=1&rows=0 
	 * 
	 */
	@RequestMapping(value = "/select", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String select(PagerForm pagerForm) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("userId", userId);
					DataGrid data = userCollectService.selectCollectList(pagerForm.getPageRequest(map));
					msg.setHearder(0, "success");
					msg.setData(data);
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
}
