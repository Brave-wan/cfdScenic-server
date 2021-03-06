package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.*;
import com.htkj.cfdScenic.app.service.*;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/invoice")
@Controller
public class InvoiceContorller extends BaseController{

	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private ShopInformationService shopInformationService;
	@Autowired
	private HotelOrderService hotelOrderService;
	@Autowired
	private RestaurantOrderService restaurantOrderService;
	@Autowired
	private GoodsOrderService goodsOrderService;
    @Autowired
    private VisitorsOrderService visitorsOrderService;
	@RequestMapping("tolist")
	public String tolist(){
		return "/background/invoice/Manager";
	}
	
	@RequestMapping("list")
	@ResponseBody
	public DataGrid list(PagerForm pagerForm ,Invoice invoice){
		SimpleDateFormat date =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time =  new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat dateTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DataGrid dataGrid = new DataGrid();
		try {
			invoice.setType(1);
			ShopUser user = (ShopUser) webContext.getSessionShopUser();	
			if(user!=null){
				ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
				if(information!=null){
					long shopId = information.getId();
					invoice.setLinkId(shopId);
				}
			} else {
				invoice.setType(0);
			}
			dataGrid = invoiceService.SelectAll(pagerForm.getPageRequest(invoice.toMap()));
			List<Map<String,Object>> list = dataGrid.getRows();
			for(int i=0;i<list.size();i++){
				String invoice_time = list.get(i).get("invoice_time")+"";
				String create_time = list.get(i).get("create_time")+"";
				if(invoice_time != null && !invoice_time.equals("null")){
					list.get(i).put("invoice_time",dateTime.format(dateTime.parse(invoice_time)));
				}
				if(create_time != null && !create_time.equals("null")){
					list.get(i).put("create_time",dateTime.format(dateTime.parse(create_time)));
				}
			}
			dataGrid.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(Long id,String code){
		Json json = new Json();
		try {
			invoiceService.deleteInvoice(id);
			ShopUser user = (ShopUser) webContext.getSessionShopUser();	
	    	if(user!=null){
	    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
	    		if(information!=null){
	    		 long shopId = information.getShopId();
	    		 if(shopId!=0){
	    			 if(shopId==1){//酒店
	    				 HotelOrder hotelOrder = new HotelOrder();
	    				 hotelOrder.setBilling(0);
	    				 hotelOrder.setOrderCode(code);
	    				 hotelOrderService.updateBillingByOrderCode(hotelOrder);
	    			 }else if(shopId==2){//饭店
	    				 RestaurantOrder restaurantOrder = new RestaurantOrder();
	    				 restaurantOrder.setBilling(0);
	    				 restaurantOrder.setOrderCode(code);
	    				 restaurantOrderService.updateBillingByOrderCode(restaurantOrder);
	    			 }else{//商品
	    				 GoodsOrder goodsOrder = new GoodsOrder();
	    				 goodsOrder.setOrderCode(String.valueOf(code));
	    				 goodsOrder.setBilling(0);
	    				 goodsOrderService.updateBillingByOrderCode(goodsOrder);
	    			 }
	    		 }
	    		}
	    	}else {
	    	    VisitorsOrder visitorsOrder = new VisitorsOrder();
                visitorsOrder.setOrderCode(String.valueOf(code));
                visitorsOrder.setInvoiceState(0);
                visitorsOrder.setIsInvoice(0);
                visitorsOrderService.updateVisitorsOrder(visitorsOrder);
            }
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			json.setMessage("删除失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	@RequestMapping("toupdate")
	public String toupdate(Long id,Model model){
		Invoice invoice = invoiceService.SelectById(id);
		model.addAttribute("model", invoice);
		return "/background/invoice/fapiao";
	}
	@RequestMapping("update")
	@ResponseBody
	public Json insert(Invoice invoice){
		Json json = new Json();
			try {
				invoice.setInvoiceTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				invoice.setInvoiceState(1);
				invoiceService.updateById(invoice);
				json.setSuccess(true);
			} catch (Exception e) {
				// TODO: handle exception
				json.setSuccess(false);
				json.setMessage("修改失败");
				e.printStackTrace();
			}
		return json;
	}
	
	@RequestMapping("dingdan")
	public String  dingdan(Long id,long code,Model model){
		Json json = new Json();
			ShopUser user = (ShopUser) webContext.getSessionShopUser();	
	    	if(user!=null){
	    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
	    		if(information!=null){
	    		 long shopId = information.getShopId();
	    		 if(shopId!=0){
	    			 if(shopId==1){//酒店
	    				 Map<String, Object> map  =  hotelOrderService.getHotelOrderByFp(code);
	    				model.addAttribute("model",map);
	    				return "/background/Order/hotelOrderyl";
	    				 
	    			 }else if(shopId==2){//饭店
	    				 try {
	    					 Map<String, Object> map = restaurantOrderService.findOrderFp(String.valueOf(code));
	    					 model.addAttribute("model", map);
	    					 return "/background/restaurantOrder/fpyl";
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
	    			 }else{//商品
	    				 try {
							
	    					 Map<String, Object> map = goodsOrderService.selectorderCode(code);
	    					 model.addAttribute("model", map);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
				        return "/background/goodsOrder/ShowPage";
	    			 }
	    		 }
	    	}
	    } else {
                Map<String, Object> map = visitorsOrderService.selectByCode(String.valueOf(code));
                model.addAttribute("model",map);
		        return "/background/visitorsOrder/fpyl";
            }
            return "";
    }
}
