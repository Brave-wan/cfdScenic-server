package com.htkj.cfdScenic.app.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.service.OrderDetailService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("/census")
public class CensusController extends BaseController{
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ShopInformationService shopInformationService;
	
	@RequestMapping("/tocensuc")
	public String tocensuc(){
		return "/background/Order/censusManager";
	}
	@RequestMapping("/censuslist")
	@ResponseBody
	public DataGrid censuslist(PagerForm pagerForm , GoodsOrder order,String dateTime){
		ShopUser user = (ShopUser) webContext.getSessionShopUser();
		ShopInformation s = shopInformationService.selectByShopUserPrimaryKey(user.getId());
		Long shopId = s.getShopId();//分类
		Long  shopInformationId= s.getId();
		order.setShopInformationId(shopInformationId);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String createTime = df.format(date);//订单生成时间
		String payTime = df.format(date);
		String state = order.getState();
//		if(state!=null){
//			if(state.equals("1")){
//				//查询订单
////				order.setOrderState(1);
//				if(dateTime != null && !dateTime.equals("")){
//				order.setCreateTime(dateTime);
//				}
////				private Integer payState;//支付状态（0未支付1已支付）
////				private Integer orderState;//订单状态（1确认订单，2取消订单 3已支付 4以退付 5已完成）
//				//（1确认订单，2已支付，3待收货，4已收货，5已完成，6申请退款，7审核通过，8退款中，9退款成功,10已驳回，11待收货（商户端））
//			}else{
				//查询售卖
				order.setOrderState(5);
				Map map = new HashMap();
				map.put("shopInformationId",order.getShopInformationId());
				switch (Integer.parseInt((s.getShopId()+""))) {
				case 1:
					map.put("orderState","2,3,4,5,7");
					break;
				case 2:
					map.put("orderState","2,3,4,5,7");
					break;
				case 3:
					map.put("orderState","2,3,4,5,6,7,8,10,11");
					break;
				case 4:
					map.put("orderState","2,3,4,5,6,7,8,10,11");
					break;
				}
				map.put("createTime",dateTime);
				map.put("payTime",payTime);
				if(dateTime != null && !dateTime.equals("")){
				order.setPayTime(dateTime);
				}
//			}
//		}else{
////			order.setOrderState(1);
//			if(dateTime != null && !dateTime.equals("")){
//			order.setCreateTime(dateTime);
//			}
//		}
		return orderDetailService.selectorderManage(pagerForm.getPageRequest(map),s.getShopId());
	}
	
	
}
