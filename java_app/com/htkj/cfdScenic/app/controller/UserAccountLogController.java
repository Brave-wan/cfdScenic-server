package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.dao.ConsumerUserDao;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.UserAddress;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.service.UserAddressService;
import com.htkj.cfdScenic.app.service.VisitorsOrderService;
import com.htkj.cfdScenic.app.service.ConsumerUserService;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/userAccountLog")
public class UserAccountLogController extends BaseController{
	@Autowired
	private HtmlTextService visitorsProfilesService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private UserAccountLogService userAccountLogService;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private VisitorsService visitorsService;
	@Autowired
	private UserAddressService userAddressService;
	@Autowired
	VisitorsOrderService visitorsOrderService;
	@Autowired
	private ConsumerUserService cus;
	/*
	 * 我的积分-兑换交易记录
	 * GET
	 * http://localhost/cfdScenic/userAccountLog/selectDealMessage
	 * 把用户的积分和该用户所交易的明细返回给用户
	 * 返回参数
		id					//交易记录id
		name				//交易名称
		tradeIntegration	//交易积分
		integration			//订单剩余积分
		remainingPoints		//用户剩余积分
		createtime			//日期
	 * 调用表 user_account_log
	 */
	@RequestMapping(value="/selectDealMessage",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String selectDealMessage(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, Object> content = new HashMap<String, Object>();
		//测试数据
		//token="";
		//userId= 1l;
		if(token!=null){
			if(userId!=null){
				try {
					//交易明细
//					Integer ShopNumber = shopUserService.selectById(userId);
//					Integer userNumber = consumerUserService.selectById(userId);
//					if(ShopNumber!=0){
//						list = userAccountLogService.selectShopMessage(userId);
//					}else if(userNumber!=0){
					list = userAccountLogService.selectUserMessage(userId);
//					}
					//剩余积分
					Integer remainingPoints = userAccountService.getRemainingPoints(userId);
					content.put("remainingPoints", remainingPoints);
					if(list.size()>0&&list.get(0)!=null)
					{
						content.put("list", list);
					}else
					{
						content.put("list", new ArrayList());
					}
					msg.setHearder(0, "success");
					msg.setData(content);
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 我的积分-积分兑换商品列表
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/shopList
	 * 把积分兑换商品列表的数据返回
	 * id
	 * head_img		(主图)
	 * name			(名称)
	 * integral		(积分价格)
	 * details		(详情)
	 * openDateId	(开放时间)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/shopList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String shopList(PagerForm pagerForm){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			DataGrid data = visitorsService.shopListLimit(pagerForm.getPageRequest(map));
			msg.setHearder(0, "success");
			msg.setData(data);
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	/*
	 * 我的积分-兑换商品详情
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/oneShopMessage
	 * 根据商品ID把商品的详情信息返回
	 * id
	 * headImg		(主图)
	 * name			(名称)
	 * integral		(积分价格)
	 * details		(详情)
	 * openDateId	(开放时间)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/oneShopMessage",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String oneShopMessage(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, String> content = new HashMap<String, String>();
		try {
			content = visitorsService.oneShopMessage(id);
			msg.setHearder(0, "success");
			msg.setData(content);
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 我的积分-兑换列表
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/convertListLimit
	 * 把用户的兑换列表明细返回给用户
	 * 返回参数
		id					//订单id
		name				//订单名称
		end_valid			//结束有效期
		head_img			//图片url
		integra_price		//积分价格
		order_describe		//订单描述
		order_state			//订单状态（1确认订单，2取消订单 3已支付 4以退付 5已完成）
		start_valid			//开始有效期
	 * 调用表 visitors_order	 visitors
	 */
	@RequestMapping(value="/convertListLimit",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String convertListLimit(PagerForm pagerForm){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		Map<String, Object> content = new HashMap<String, Object>();
		//测试数据
		//token="";
		//userId= 1l;
		DataGrid data = null;
		if(token!=null){
			if(userId!=null){
				try {
					content.put("userId", userId);
					data = visitorsOrderService.selectOrderList(pagerForm.getPageRequest(content));
					msg.setHearder(0, "success");
					msg.setData(data);
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 我的积分-兑换列表详情
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/convertListDetails
	 * 根据订单号id把信息返回
	 * 请求参数
	 * id					//订单id
	 * 返回参数
		id					//订单id
		name				//订单名称
		end_valid			//结束有效期
		head_img			//图片url
		integra_price		//积分价格
		order_describe		//订单描述
		order_state			//订单状态（1确认订单，2取消订单 3已支付 4以退付 5已完成）
		start_valid			//开始有效期
	 * 调用表 visitors_order	 visitors
	 */
	@RequestMapping(value="/convertListDetails",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String convertListDetails(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, Object> content = new HashMap<String, Object>();
		Map<String, Object> visitorsMessage = new HashMap<String, Object>();
		UserAddress addressMessage = new UserAddress();
		try {
			visitorsMessage = visitorsOrderService.selectById(id);
			if(visitorsMessage.get("address_id")!=null&&visitorsMessage.get("address_id")!="")
			{
			Object address_id = visitorsMessage.get("address_id");
			
				Long addressId = Long.parseLong(address_id.toString());
				addressMessage = userAddressService.selectById(addressId);
			}
			content.put("visitorsMessage",visitorsMessage);
			content.put("addressMessage",addressMessage);
			msg.setHearder(0, "success");
			msg.setData(content);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/*
	 * 我的积分-马上兑换
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/immediatelyChange
	 * 请求参数
	 * id					//商品id
	 * 返回参数
	 content
	 	id						//id
		head_img				//图片url
		integral				//积分价格
		name					//名称
	address
		user_name			//名字
		telphone			//电话
		place_address		//所在地址
		detail_address		//详细地址
		postcode			//邮编
	 * 调用表 user_address visitors
	 */
	@RequestMapping(value="/immediatelyChange",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String immediatelyChange(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		//测试数据
		//token="";
		//userId= 1l;
		Map<String, String> address = new HashMap<String, String>();
		Map<String, String> content = new HashMap<String, String>();
		Map<String, Object> message = new HashMap<String, Object>();
		if(token!=null){
			if(userId!=null){
				try {
					//默认的收货地址
					address = userAddressService.selectDefaultAddress(userId);
					//商品的详情
					content = visitorsService.oneShopMessage(id);
					msg.setHearder(0, "success");
					message.put("content", content);
					message.put("address", address);
					msg.setData(message);
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	
	/*
	 * 我的积分-确认兑换
	 * GET
	 * http://localhost:8080/cfdScenic/userAccountLog/confirmTheChange
	 * 把订单需要的信息存进数据库
	 * 请求参数
		name				//订单名称
		orderDescribe		//订单描述
		price				//原价
		realPrice			//应付价格
		integraPrice		//积分价格
		startValid			//开始有效期
		endValid			//结束有效期
		visitorsId  		//景点id
		create_time   now
	 * 调用表 visitors_order
	 */
	@RequestMapping(value="/confirmTheChange",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String confirmTheChange(VisitorsOrder visitorsOrder,Long addressId){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		//测试数据
		//token="";
		//userId=1l;
		if(token!=null){
			if(userId!=null){
				try {
					visitorsOrder.setId(GenerateSequenceUtil.getUniqueId());
					visitorsOrder.setQuantity(1);
					visitorsOrder.setType(1);
					visitorsOrder.setPayState(0);
					visitorsOrder.setOrderState(1);
					visitorsOrder.setUserId(userId);
					visitorsOrder.setOrderCode(GenerateSequenceUtil.getUniqueId().toString());
					visitorsOrder.setAddressId(addressId);
					visitorsOrderService.insertMessage(visitorsOrder);
					msg.setHearder(0, "success");
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	/**
	 * 我的积分-确认支付
	 * GET
	 * http://localhost/cfdScenic/userAccountLog/confirmPayment
	 * 把订单需要的信息存进数据库
	 * 请求参数
		orderId //订单ID
		balance	//余额
		price	//配送费
		integration	//积分
		payType	//支付方式
	 * 调用表 user_account_log
	 */
	@RequestMapping(value="/confirmPayment",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String confirmPayment(Long orderId,Double balance,Double price,Integer integration,Integer payType,String name,String passWord){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		if(token!=null){
			if(userId!=null){
				try {
					String userPassWord = cus.getUserPassWordById(userId);
					if(payType != 4){
					if(passWord != null && !passWord.equals("")){
					if((MD5.GetMD5Code(passWord)).equals(userPassWord)){
					//剩余积分
					Integer remainingPoints = userAccountService.getRemainingPoints(userId);
					//交易过后积分
					Integer integral =remainingPoints-integration;
					
					if(integral>0){
							UserAccountLog userAccountLog = new UserAccountLog();
							// 默认信息
							userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
							userAccountLog.setType(3);
							userAccountLog.setName(name);
							userAccountLog.setPrice(price);
							userAccountLog.setBalance(balance);
							userAccountLog.setTradeIntegration(integration);
							userAccountLog.setIntegration(remainingPoints);
							userAccountLog.setUserId(userId);
							userAccountLog.setShopId(orderId);
							userAccountLog.setExtractType(0);
							userAccountLogService.insertMessage(userAccountLog);

							// 更新订单表
							String orderCode = visitorsOrderService.getOrderCodeByOrderId(orderId);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String payTime = format.format(new Date());
							map.put("userId", userId);
							map.put("payTime", payTime);
							map.put("orderCode",orderCode);
							visitorsOrderService.updateStateMessage(map);
							// 更新用户帐户信息
							content.put("userId", userId);
							content.put("integral", integral);
							if(payType == 0){
								if(balance < price){
									msg.setHearder(5, "余额不足");
									json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
									return json;
								}else{
								content.put("balance",balance - price);
								}
							}
							userAccountService.updateMoney(content);
							msg.setHearder(0, "success");
					}else{
						msg.setHearder(4, "积分不足！！！");
					}
				}else{
					msg.setHearder(5, "passWord is error");
				}
				}else{
					msg.setHearder(6, "passWord is null");
				}
				}else{

					//剩余积分
					Integer remainingPoints = userAccountService.getRemainingPoints(userId);
					//交易过后积分
					Integer integral =remainingPoints-integration;
					
					if(integral>0){
							UserAccountLog userAccountLog = new UserAccountLog();
							// 默认信息
							userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
							userAccountLog.setType(3);
							userAccountLog.setName(name);
							userAccountLog.setPrice(price);
							userAccountLog.setBalance(balance);
							userAccountLog.setTradeIntegration(integration);
							userAccountLog.setIntegration(remainingPoints);
							userAccountLog.setUserId(userId);
							userAccountLog.setShopId(orderId);
							userAccountLog.setExtractType(0);
							userAccountLogService.insertMessage(userAccountLog);

							// 更新订单表
							String orderCode = visitorsOrderService.getOrderCodeByOrderId(orderId);
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String payTime = format.format(new Date());
							map.put("userId", userId);
							map.put("payTime", payTime);
							map.put("orderCode",orderCode);
							visitorsOrderService.updateStateMessage(map);
							// 更新用户帐户信息
							content.put("userId", userId);
							content.put("integral", integral);
							if(payType == 0){
								if(balance < price){
									msg.setHearder(5, "余额不足");
									json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
									return json;
								}else{
								content.put("balance",balance - price);
								}
							}
							userAccountService.updateMoney(content);
							msg.setHearder(0, "success");
					}else{
						msg.setHearder(4, "积分不足！！！");
					}
				
				}
				} catch (Exception e) {
					msg.setHearder(1, "error");
					e.printStackTrace();
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
}
