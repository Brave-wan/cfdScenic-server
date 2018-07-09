package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.Invoice;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.service.InvoiceService;
import com.htkj.cfdScenic.app.service.RestaurantOrderService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 饭店订单
 * @author wangfenglong
 * @date 2016/10/8 00089:09.
 */
@Controller
@RequestMapping("/background/restaurantOrderManage")
public class RestaurantOrderManageController extends BaseController{

    @Autowired
    private RestaurantOrderService restaurantOrderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountLogService userAccountLogService;
    @Autowired
    private ShopInformationService shopInformationService;
    @Autowired
    private InvoiceService invoiceService;

    SimpleDateFormat date =  new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat time =  new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat dateTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 判断订单跳转类型
     * 张腾跃
     * 2016年10月19日13:52:38
     */
    @RequestMapping("todingdan")
    public String todingdan(){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId =  information.getShopId();
    		 if(shopId!=0){
    			 if(shopId==1){//酒店
    				 return "redirect:/hotelOrder/tohotelOrderManage";
    			 }else if(shopId==2){//饭店
    				 return "redirect:/background/restaurantOrderManage/toRestaurantOrderManage";
    			 }else{//商品
    				 return "redirect:/background/goodsOrderManage/toGoodsOrderManage";
    			 }
    		 }
    		}
    	}
    	return "";
    }
    /**
     * 判断订单跳转类型
     * 张腾跃
     * 2016年10月19日13:52:38
     */
    @RequestMapping("ddkp")
    public String ddkp(){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getShopId();
    		 if(shopId!=0){
    			 if(shopId==1){//酒店
    				 return "/background/restaurantOrder/jiudianManager";
    			 }else if(shopId==2){//饭店
    				 return "/background/restaurantOrder/fandianManager";
    			 }else{//商品
    				 return "/background/restaurantOrder/shangpinManager";
    			 }
    		 }
    		}
    	}
    	return "";
    }
    @RequestMapping("/getgoodsList")
    @ResponseBody
    public DataGrid getgoodsList(PagerForm page,HotelOrder hotelOrder){
        Map<String,Object> map = new HashMap();
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getId();
    		 map.put("shopInformationId", shopId);
    		}
    	}
        return restaurantOrderService.getRestaurantOrderList(page.getPageRequest(map));
    }
    @RequestMapping("/getrestaurantOrder")
    @ResponseBody
    public DataGrid getrestaurantOrder(PagerForm page,HotelOrder hotelOrder){
        Map<String,Object> map = new HashMap();
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getId();
    		 map.put("shopInformationId", shopId);
    		}
    	}
        return restaurantOrderService.getRestaurantOrderList(page.getPageRequest(map));
    }
    //饭店开票
    @RequestMapping("/getrestaurantOrderkp")
    @ResponseBody
    public DataGrid getrestaurantOrderkp(PagerForm page,RestaurantOrder restaurantOrder){
        Map<String,Object> map = new HashMap();
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
        DataGrid dataGrid = new DataGrid();
        try {
        	map.put("orderState","4");
         	if(user!=null){
         		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
         		if(information!=null){
         		 long shopId = information.getId();
         		 map.put("shopInformationId", shopId);
         		}
         	}
        	dataGrid = restaurantOrderService.getgroupOrderList(page.getPageRequest(map));
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
    
    
    /**
     *饭店订单 - 主页
     * @return
     */
    @RequestMapping("/toRestaurantOrderManage")
    public String toRestaurantOrderManage(){
        return "/background/restaurantOrder/Manager";
    }

    /**
     * 饭店订单 - 列表
     * @param page
     * @param restaurantOrder
     * @return
     */
    @RequestMapping("/getRestaurantOrderList")
    @ResponseBody
    public DataGrid getRestaurantOrderList(PagerForm page , RestaurantOrder restaurantOrder , String nickName){
        Map<String,Object> map = new HashMap();
        map.put("orderCode",restaurantOrder.getOrderCode());
        map.put("createTime",restaurantOrder.getCreateTime());
        map.put("orderState",restaurantOrder.getOrderState());
        map.put("nickName",nickName);
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getId();
    		 map.put("shopInformationId", shopId);
    		}
    	}
        return restaurantOrderService.getRestaurantOrderList(page.getPageRequest(map));
    }
    /**
     * 饭店订单 - 列表
     * @param page
     * @param restaurantOrder
     * @return
     */
    @RequestMapping("/toFinshOrder")
    @ResponseBody
    public Json toFinshOrder(String orderCode){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
        return restaurantOrderService.toFinshOrder(orderCode,user.getShopInformationId());
    }

/**
 * 张腾跃
 * 2016年10月19日18:12:09
 * 跳转至开发票页面updateBybilling
 */
    @RequestMapping("tokaipiao")
    public String kaipiao(Model model,String code){
    	model.addAttribute("code", code);
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();	
      	if(user!=null){
      		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
      		if(information!=null){
      		 long shopId = information.getId();
      		model.addAttribute("linkId", shopId);
      		}
      	}
    	return "/background/restaurantOrder/fapiao";
    }
    
    
    /**
     * 开发票
     * 张腾跃
     * 2016年10月19日15:06:48
     */
    @RequestMapping("fapiao")
    @ResponseBody
    public Json fapiao(Invoice invoice){
    	Json json  = new Json();
    	try {
    		invoice.setId(GenerateSequenceUtil.getUniqueId());
    		invoice.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    		invoiceService.saveInvoice(invoice);
    		try {
    			RestaurantOrder restaurantOrder = new RestaurantOrder();
    			restaurantOrder.setOrderCode(invoice.getOrderCode());
    			restaurantOrder.setBilling(1);
    			restaurantOrderService.updateBillingByOrderCode(restaurantOrder);
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
     * 订单 - 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Long id , ModelMap model){
        try {
            Map<String, Object> map = restaurantOrderService.selectByPrimaryKey(id);
            model.addAttribute("model", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/background/restaurantOrder/ShowPage";
    }


    /**
     * 订单 - 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteRestaurantOrder")
    @ResponseBody
    public Json deleteVisitorsOrder(Long id){
        Json json = new Json();
        try {
            restaurantOrderService.deleteVisitorsOrder(id);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 修改订单状态
     * @param restaurantOrder
     * @return
     */
    @RequestMapping("/updateRestaurantOrder")
    @ResponseBody
    public Json updateRestaurantOrder(RestaurantOrder restaurantOrder){
        Json json = new Json();
        UserAccountLog userAccountLog = new UserAccountLog();
        try {
            if (restaurantOrder.getOrderState() == 6) {
                UserAccount userAccount = userAccountService.selectByUserId(restaurantOrder.getUserId());
                BigDecimal b1 = new BigDecimal(userAccount.getBalance());
                BigDecimal b2 = new BigDecimal(restaurantOrder.getRealPrice());
                userAccount.setUserId(restaurantOrder.getUserId());
                userAccount.setBalance((b1.add(b2)).doubleValue());
                userAccountService.addBalanceByUserId(userAccount);

                userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
                userAccountLog.setType(4);
                userAccountLog.setTradeIntegration(0);
                userAccountLog.setIntegration(0);
                userAccountLog.setPrice(restaurantOrder.getRealPrice());
                userAccountLog.setBalance((b1.add(b2)).doubleValue());
                userAccountLog.setUserId(restaurantOrder.getUserId());
                userAccountLog.setExtractType(0);
                userAccountLogService.insertMessage(userAccountLog);
            }

            restaurantOrderService.updateByOrderCode(restaurantOrder);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
