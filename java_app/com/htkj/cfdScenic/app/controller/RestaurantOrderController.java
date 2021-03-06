package com.htkj.cfdScenic.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.HotelOrderService;
import com.htkj.cfdScenic.app.service.RestaurantOrderService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("/restaurantOrder")
public class RestaurantOrderController extends BaseController{
	@Autowired
	private RestaurantOrderService restaurantOrderService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	private ShopUserService shopUserService;
	
	/*
	 * 饭店版--订单中心--订单列表
	 * post
	 * http://localhost:8080/cfdScenic/restaurantOrder/orderList
	 * 用户新增添加收货地址    
	 * 请求参数
		id,				//订单id
		name,			//订单名称
		quantity,		//房间数量
		start_date,		//入住时间
		end_date,		//离店时间
		price,			//原价
		real_price,		//应付价格
		order_code,		//订单号
		check_days		//入住天数
		
		
		
		status			//状态(1未使用2已使用3已过期)
		type			//类型(0单品1套餐)
		
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="orderList",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderList(Integer status,PagerForm pagerForm,Integer type){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		//测试数据
		//token = "";
		//userId = 1l;
		if(token!=null){
			if(userId!=null){
				
				ShopUser user=	shopUserService.selectByUserId(userId);
				Long shopInformationId=user.getShopInformationId();
				map.put("sfId", shopInformationId);
				
				switch (status) {
				case 1:	//未使用
					map.put("status","2");
					break;
				case 2:	//已使用
					map.put("status","4");
					break;
				case 3:	//已过期
					map.put("status","5");
					break;
				}
				try {
					map.put("type",type);
					//获取订单列表
					DataGrid data = restaurantOrderService.findOrderList(pagerForm.getPageRequest(map));
					List<Map<String,Object>> orderList = data.getRows();
					Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
					for (int i = 0; i < orderList.size(); i++) {
						Map<String,Object> go = orderList.get(i);
						String orderCode = go.get("order_code")+"";
						if (listMap.containsKey(orderCode)) {
							listMap.get(orderCode).add(go);
						} else {
							List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
							l.add(go);
							listMap.put(orderCode,l);
						}
					}
					List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
					Iterator<String> it = listMap.keySet().iterator();
					while(it.hasNext()){
						String key = it.next()+"";
						System.out.println(listMap.get(key));
						List<Map<String,Object>> goList = listMap.get(key);
						returnlist.add(goList);
					}
					data.setRows(returnlist);
					msg.setHearder(0, "success");
					msg.setData(data);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
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
	/**
	 * 饭店版--订单中心--订单详情
	 * 调用表 hotel_order
	 * restaurantOrder/orderDetail
	 */
	@RequestMapping(value="orderDetail",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderDetail(Integer goodsType,Long orderCode,Long siId){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		ResponseMsg msg = new ResponseMsg();
		try {
		//判断token是否为空
		if(token!=null){
			//判断用户是否存在
			if(userId!=null){
				Map goods = new HashMap();
				goods.put("orderCode",orderCode);
				goods.put("siId",siId);
				//判断是单品还是套餐
				switch (goodsType) {
				case 0:
					//单品。查出所有的数据，再分类
					list = restaurantOrderService.getOrderDetailByShop(goods);
					msg.setData(list);
					break;
				case 1:
					//套餐。通过套餐获取商品id，根据id查单品
					Map rp = restaurantOrderService.getRestaurantOrderByOrderCode(goods);
					if(rp != null){
						if(rp.get("goods_ids") != null){
						String ids = rp.get("goods_ids")+"";
						Map sg = new HashMap();
						sg.put("ids",ids);
						List sgList = restaurantOrderService.getShopGoodsByIds(sg);
						Map rMap = new HashMap();
						rMap.put("detail", rp);
						rMap.put("shopGoodsList", sgList);
						msg.setData(rMap);
						}
					}
					break;
				}
				msg.setHearder(0, "ok");
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		} catch (Exception e) {
			msg.setHearder(1, "error!");
			e.printStackTrace();
		}
		String json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	/*
	 * 饭店版--订单中心--订单搜索
	 * post
	 * http://localhost:8080/cfdScenic/restaurantOrder/selectOrderBycode
	 * 用户新增添加收货地址    
	 * 请求参数
		id,				//订单id
		name,			//订单名称
		quantity,		//房间数量
		start_date,		//入住时间
		end_date,		//离店时间
		price,			//原价
		real_price,		//应付价格
		order_code,		//订单号
		check_days		//入住天数
		
		
		
		status			//状态(1未使用2已使用3已过期)
		type			//类型(0单品1套餐)
		
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="selectOrderBycode",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectOrderBycode(String codeOrname){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		//测试数据
		//token = "";
		//userId = 1l;
		if(token!=null){
			if(userId!=null){
				
				ShopUser user=	shopUserService.selectByUserId(userId);
				Long shopInformationId=user.getShopInformationId();
				map.put("sfId", shopInformationId);
				map.put("codeOrname", codeOrname);
				
				try {

					//获取订单列表
					List<Map<String,Object>> data = restaurantOrderService.selectOrderBycode(map);
					msg.setHearder(0, "success");
					if(data.size()>0&&data.get(0)!=null)
					{
						msg.setData(data);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
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
	
	
	
	
	
	/*
	 * 饭店版--订单中心--取消订单
	 * post
	 * http://localhost:8080/cfdScenic/restaurantOrder/cancelOrder
	 * 根据用户提供的订单号取消订单    
	 * 请求参数
		orderCode		//订单号
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="cancelOrder",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(String orderCode,Long siId){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		ResponseMsg msg = new ResponseMsg();
		//测试数据
		//token = "";
		//userId = 1l;
		if(token!=null){
			if(userId!=null){
				try {
					Map ro = new HashMap();
					ro.put("orderCode",orderCode);
					ro.put("siId",siId);
					//查订单信息
					List<RestaurantOrder> restaurantOrder = restaurantOrderService.findOrder(orderCode);
					//查是否以经支付
					for(int i=0;i<restaurantOrder.size();i++){
					Integer payState = restaurantOrder.get(i).getPayState();
					if(payState!=null&&payState==0){
						//更新状态为(取消订单)2
						restaurantOrderService.deleteState(ro);
					}else{
						//查用户支付钱数
						Double realPrice = restaurantOrder.get(i).getRealPrice();
						//查用户id
						Long userId2 = restaurantOrder.get(i).getUserId();
						//查用户的帐户信息
						UserAccount userAccountMessage = userAccountService.findByUserId(userId2);
						//获取用户余额
						Double balance = userAccountMessage.getBalance();
						//更新用户钱数
						userAccountMessage.setBalance(realPrice+balance);
						userAccountService.updateBalanceMessage(userAccountMessage);
						}
					}
					//更新状态为(取消订单)2
					restaurantOrderService.updateState(ro);
					msg.setHearder(0, "success");
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
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
	
	
	
	
	/*
	 * 饭店版--订单中心--订单核销
	 * post
	 * http://localhost:8080/cfdScenic/restaurantOrder/orderVerification
	 * 根据用户提供的订单号取消订单    
	 * 请求参数
		orderCode		//订单号
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="orderVerification",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderVerification(String orderCode){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		//测试数据
		//token = "";
		//userId = 1l;
		if(token!=null){
			if(userId!=null){
				try {
					//查订单信息
					RestaurantOrder restaurantOrder = new RestaurantOrder();
					restaurantOrder.setOrderCode(orderCode);
					restaurantOrder.setOrderState(4);
					restaurantOrderService.updateOrderState(restaurantOrder);
					msg.setHearder(0, "success");
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
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
	
	/**
	 * 商铺 - 饭店 - 退款订单
	 * restaurantOrder/restaurantRefundOrder   
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="restaurantRefundOrder",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String restaurantRefundOrder(Long siId,Integer type){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		ResponseMsg msg = new ResponseMsg();
		List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
		if(token!=null){
			if(userId!=null){
				try {
					Map para = new HashMap();
					para.put("siId",siId);
					//查订单信息
					switch (type) {
					case 1://待审核
						para.put("orderState","3");
						orderList = restaurantOrderService.shopFindRestaurantOrder(para);
						break;
					case 2://已退款
						para.put("orderState","6");
						orderList = restaurantOrderService.shopFindRestaurantOrder(para);
						break;
					}
					if(orderList.size() > 0 && orderList.get(0) != null){
					Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
					for (int i = 0; i < orderList.size(); i++) {
						Map<String,Object> go = orderList.get(i);
						String orderCode = go.get("order_code")+"";
						if (listMap.containsKey(orderCode)) {
							listMap.get(orderCode).add(go);
						} else {
							List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
							l.add(go);
							listMap.put(orderCode,l);
						}
					}
					List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
					Iterator<String> it = listMap.keySet().iterator();
					while(it.hasNext()){
						String key = it.next()+"";
						System.out.println(listMap.get(key));
						List<Map<String,Object>> goList = listMap.get(key);
						returnlist.add(goList);
					}
					msg.setHearder(0, "ok");
					msg.setData(returnlist);
					}else{
						msg.setHearder(0, "ok");
						msg.setData(new ArrayList());
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
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
