package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.Invoice;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.service.InvoiceService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.HotelOrderService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/hotelOrder")
public class HotelOrderController extends BaseController{
	@Autowired
	private HotelOrderService hotelOrderService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private UserAccountLogService userAccountLogService;
	@Autowired
    private ShopInformationService shopInformationService;
	@Autowired
	private InvoiceService invoiceService;
	
	SimpleDateFormat date =  new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat time =  new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat dateTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/*
	 * 酒店版--订单中心--订单列表
	 * post
	 * http://localhost/cfdScenic/hotelOrder/orderList
	 * 用户新增添加收货地址    
	 * status   1未使用 2已使用 3已过期
	 * 
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
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="orderList",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderList(Integer status,PagerForm pagerForm){
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
				case 1://未使用
					map.put("status","2,7");
					break;
				case 2://已使用
					map.put("status","4");
					break;
				case 3://已过期
					map.put("status","5");
					break;
				}
				try {
					//获取订单列表
					DataGrid data = hotelOrderService.findOrderList(pagerForm.getPageRequest(map));
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
	
	
	
	
	
	
	
	
	/*
	 * 酒店版--订单中心--订单搜索
	 * post
	 * http://localhost:8080/cfdScenic/hotelOrder/selectOrderBycode
	 * 用户新增添加收货地址    
	 * codeOrname  订单号或者订单人
	 * 
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
					List<Map<String,Object>> data = hotelOrderService.selectOrderBycode(map);
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
	 * 酒店版--订单中心--取消订单
	 * post
	 * http://localhost:8080/cfdScenic/hotelOrder/cancelOrder
	 * 根据用户提供的订单号取消订单    
	 * 请求参数
		        		//订单号
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="cancelOrder",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(String orderCode){
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
					Map go = new HashMap();
					go.put("orderCode",orderCode);
					//查订单信息
					HotelOrder hotelOrder = hotelOrderService.findOrder(orderCode);
					//查是否以经支付
					Integer payState = hotelOrder.getPayState();
					if(payState!=null&&payState==0){
						//更新状态
						hotelOrderService.deleteState(go);
					}else{
						//查用户支付钱数
						Double realPrice = hotelOrder.getRealPrice();
						//查用户id
						Long userId2 = hotelOrder.getUserId();
						//查用户的帐户信息
						UserAccount userAccountMessage = userAccountService.findByUserId(userId2);
						//获取用户余额
						Double balance = userAccountMessage.getBalance();
						//更新用户钱数
						userAccountMessage.setBalance(realPrice+balance);
						userAccountService.updateBalanceMessage(userAccountMessage);
						//更新状态
						hotelOrderService.updateState(go);
					}
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
	 * 酒店版--订单中心--订单核销
	 * post
	 * http://localhost:8080/cfdScenic/hotelOrder/orderVerification
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
		if(token!=null){
			if(userId!=null){
				try {
					//查订单信息
					HotelOrder hotelOrder = hotelOrderService.findOrder(orderCode);
					if(hotelOrder!=null)
					{
							hotelOrder.setOrderState(4);
							hotelOrderService.updateOrderState(hotelOrder);
							msg.setHearder(0, "success");
					}else
					{
						msg.setHearder(4, "订单不存在");
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
	/**
	 * 商铺 - 酒店 - 退款订单
	 * http://localhost:8080/cfdScenic/hotelOrder/hotelRefundOrder
	 * 调用表 hotel_order
	 */
	@RequestMapping(value="hotelRefundOrder",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String hotelRefundOrder(Long siId,Integer type){
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
						orderList = hotelOrderService.shopFindHotelOrder(para);
						break;
					case 2://已退款
						para.put("orderState","6");
						orderList = hotelOrderService.shopFindHotelOrder(para);
						break;
					}
					msg.setHearder(0, "ok");
					msg.setData(orderList);
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
	 * 查看订单
	 * 张腾跃
	 * 2016年9月30日14:56:09
	 */
	@RequestMapping("/tohotelOrderManage")
	public String tohotelOrderManage(){
		return "/background/Order/hotelOrderManager";
	}
	@RequestMapping("/findall")
	@ResponseBody
	public DataGrid findall(PagerForm pagerForm,HotelOrder hotelOrder){ 
		ShopUser user = (ShopUser) webContext.getSessionShopUser();	
		DataGrid dataGrid = new DataGrid();
		try {
			if(user!=null){
				ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
				if(information!=null){
					hotelOrder.setShopInformationId(information.getId());
				}
			}
			dataGrid =hotelOrderService.findall(pagerForm.getPageRequest(hotelOrder.toMap())); 
			List<Map<String,Object>> list = dataGrid.getRows();
			for(int i=0;i<list.size();i++){
				String start_date = list.get(i).get("start_date")+"";
				String end_date = list.get(i).get("end_date")+"";
				String create_time = list.get(i).get("create_time")+"";
				String pay_time = list.get(i).get("pay_time")+"";
				String refund_time = list.get(i).get("refund_time")+"";
				if(start_date != null && !start_date.equals("null")){
				list.get(i).put("start_date",dateTime.format(dateTime.parse(start_date)));
				}
				if(end_date != null && !end_date.equals("null")){
				list.get(i).put("end_date",dateTime.format(dateTime.parse(end_date)));
				}
				if(create_time != null && !create_time.equals("null")){
				list.get(i).put("create_time",dateTime.format(dateTime.parse(create_time)));
				}
				if(pay_time != null && !pay_time.equals("null")){
				list.get(i).put("pay_time",dateTime.format(dateTime.parse(pay_time)));
				}
				if(refund_time != null && !refund_time.equals("null")){
				list.get(i).put("refund_time",dateTime.format(dateTime.parse(refund_time)));
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	@RequestMapping("/findallkp")
	@ResponseBody
	public DataGrid findallkp(PagerForm pagerForm,HotelOrder hotelOrder){ 
		ShopUser user = (ShopUser) webContext.getSessionShopUser();	
		DataGrid dataGrid = new DataGrid();
		try {
			if(user!=null){
	    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
	    		if(information!=null){
	    		hotelOrder.setShopInformationId(information.getId());
	    		}
	    	}
			dataGrid = hotelOrderService.findall(pagerForm.getPageRequest(hotelOrder.toMap()));
			List<Map<String,Object>> list = dataGrid.getRows();
			for(int i=0;i<list.size();i++){
				String start_date = list.get(i).get("start_date")+"";
				String end_date = list.get(i).get("end_date")+"";
				String create_time = list.get(i).get("create_time")+"";
				String pay_time = list.get(i).get("pay_time")+"";
				String refund_time = list.get(i).get("refund_time")+"";
				if(start_date != null && !start_date.equals("null")){
				list.get(i).put("start_date",dateTime.format(dateTime.parse(start_date)));
				}
				if(end_date != null && !end_date.equals("null")){
				list.get(i).put("end_date",dateTime.format(dateTime.parse(end_date)));
				}
				if(create_time != null && !create_time.equals("null")){
				list.get(i).put("create_time",dateTime.format(dateTime.parse(create_time)));
				}
				if(pay_time != null && !pay_time.equals("null")){
				list.get(i).put("pay_time",dateTime.format(dateTime.parse(pay_time)));
				}
				if(refund_time != null && !refund_time.equals("null")){
				list.get(i).put("refund_time",dateTime.format(dateTime.parse(refund_time)));
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	hotelOrder.setOrderState(4);
		return dataGrid;
	}
	@RequestMapping("tokaipiao")
	public String tokaipiao(long id,Long code,Model model){
		model.addAttribute("id", id);
    	model.addAttribute("code", code);
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
      	if(user!=null){
      		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
      		if(information!=null){
      		 long shopId = information.getId();
      		model.addAttribute("linkId", shopId);
      		}
      	}
		return "/background/Order/fapiao";
	}
	/**
	 * 修改订单（开票）
	 * 张腾跃
	 * 2016年10月19日17:25:56
	 */
	@RequestMapping("updatekaipiao")
	@ResponseBody
	public Json updatekaipiao(Invoice invoice,Long hotelId){
		Json json = new Json();
		try {
    		invoice.setId(GenerateSequenceUtil.getUniqueId());
    		invoice.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    		invoiceService.saveInvoice(invoice);
    		
    		try {
    			HotelOrder hotelOrder = new HotelOrder();
    			hotelOrder.setId(hotelId);
    			hotelOrder.setBilling(1);
    			hotelOrderService.updatekaipiao(hotelOrder);
    			json.setSuccess(true);
    		} catch (Exception e) {
    			// TODO: handle exception
    			json.setSuccess(false);
    			json.setMessage("开票失败");
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			// TODO: handle exception
			json.setSuccess(false);
			json.setMessage("开票失败");
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 预览
	 * 张腾跃
	 * 2016年10月8日15:50:19
	 */
	@RequestMapping("/yl")
	public String yl(Long id,Model m){
		Map<String, Object> map = hotelOrderService.getHotelOrderByOrderIdyl(id);
		m.addAttribute("model",map);
		return "/background/Order/hotelOrderyl";
	}
	/**
	 * 更改状态
	 * 张腾跃
	 * 2016年9月30日15:01:09
	 * @param hotelOrder
	 * @return
	 */
	@ResponseBody
	@RequestMapping("hotelorderupdate")
	public Json hotelorderupdate(HotelOrder hotelOrder,ModelMap model){
		Json json = new Json();
		 UserAccountLog userAccountLog = new UserAccountLog();
		UserAccount userAccount = userAccountService.selectByUserId(hotelOrder.getShopInformationId());
		Double usera = userAccount.getBalance();
		Double price = hotelOrder.getRealPrice();
		Double balance = usera-price;
		if(balance<0){
			json.setSuccess(false);
			json.setMessage("余额不足");
		}else{
			UserAccount userAccount1 = userAccountService.selectByUserId(hotelOrder.getUserId());
			Double userb = userAccount1.getBalance();
			Double b = userb+price;
			userAccount1.setBalance(b);
			userAccount1.setUserId(hotelOrder.getUserId());
			try {
				//更改状态
				hotelOrderService.updateHotelOrderById(hotelOrder);
				//只有状态选择为退款事才进行余额操作
				if(hotelOrder.getOrderState()==3){
					try {
						//退款至用户账户表余额中
						userAccountService.updateBalanceByShopUserId(userAccount1);
						//保存至用户交易记录表里面
						userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
						userAccountLog.setType(4);
						userAccountLog.setTradeIntegration(0);
						userAccountLog.setExtractType(0);
						if(userAccount1.getIntegration()==null){
							userAccount1.setIntegration(0);
						}
						userAccountLog.setIntegration(userAccount1.getIntegration());
						userAccountLog.setPrice(price);
						userAccountLog.setBalance(b);
						userAccountLog.setUserId(hotelOrder.getUserId());
						userAccountLog.setShopId(hotelOrder.getShopInformationId());
						userAccountLog.setName("酒店退款");
						userAccountLog.setExtractType(0);
						userAccountLogService.insertMessage(userAccountLog);
						json.setSuccess(true);
					} catch (Exception e) {
						json.setSuccess(false);
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				json.setSuccess(false);
				e.printStackTrace();
				json.setMessage(e.getMessage());
			}
		}
		return json;
		
	}
	
	
}
