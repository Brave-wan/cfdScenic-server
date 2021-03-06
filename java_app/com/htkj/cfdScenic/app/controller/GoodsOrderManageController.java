package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.Express;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.Invoice;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.service.GoodsOrderService;
import com.htkj.cfdScenic.app.service.InvoiceService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

import oracle.net.aso.i;

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
import java.util.Map;

/**
 * @author wangfenglong
 * @date 2016/10/11 00119:57.
 */
@Controller
@RequestMapping("/background/goodsOrderManage")
public class GoodsOrderManageController extends BaseController {

    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountLogService userAccountLogService;
    @Autowired
    private ShopInformationService shopInformationService;
    @Autowired
    private InvoiceService invoiceService;
    
    /**
     * 商品订单 - 首页
     * @return
     */
    @RequestMapping("/toGoodsOrderManage")
    public String toGoodsOrderManage(){
        return "/background/goodsOrder/Manager";
    }

    /**
     * 商品订单 - 列表
     * @param page
     * @param goodsOrder
     * @return
     */
    @RequestMapping("/getGoodsOrderList")
    @ResponseBody
    public DataGrid getGoodsOrderList(PagerForm page , GoodsOrder goodsOrder , String nickName) {
        Map<String,Object> map = new HashMap();
        map.put("orderCode",goodsOrder.getOrderCode());
        map.put("createTime",goodsOrder.getCreateTime());
        map.put("orderState",goodsOrder.getOrderState());
        map.put("nickName",nickName);
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getId();
    		 map.put("shopInformationId", shopId);
    		}
    	}
        return goodsOrderService.getGoodsOrderList(page.getPageRequest(map));
    }
    @RequestMapping("/getGoodsOrderkpList")
    @ResponseBody
    public DataGrid getGoodsOrderkpList(PagerForm page , GoodsOrder goodsOrder , String nickName) {
        Map<String,Object> map = new HashMap();
        map.put("orderState",5);
        map.put("isDeliverFee", 1);
        ShopUser user = (ShopUser) webContext.getSessionShopUser();	
    	if(user!=null){
    		ShopInformation information =  shopInformationService.selectByShopUserPrimaryKey(user.getId());
    		if(information!=null){
    		 long shopId = information.getId();
    		 map.put("shopInformationId", shopId);
    		}
    	}
        return goodsOrderService.getGoodsOrderList(page.getPageRequest(map));
    }
    /**
     * 商品订单 - 详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showPage")
    public String showPage(Long id , ModelMap model){
        try {
            Map<String, Object> map = goodsOrderService.selectByPrimaryKey(id);
            model.addAttribute("model", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/background/goodsOrder/ShowPage";
    }
/**
 * 修改订单运费
 */
    @ResponseBody
    @RequestMapping("updateyunfei")
    public Json updateyunfei(GoodsOrder goodsOrder,Long id,String deliverFee) {
    	Json json = new Json();
    	try {
			goodsOrderService.updateByOrder(goodsOrder);
			json.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			json.setSuccess(false);
		}
		return json;
	}
    /**
     * 商品订单 - 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteGoodsOrder")
    @ResponseBody
    public Json deleteVisitorsOrder(Long id){
        Json json = new Json();
        try {
            goodsOrderService.deleteGoodsOrderById(id);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    /**
     * 跳转至开发票页面
     * 张腾跃
     * 2016年10月19日15:11:28
     */
    @RequestMapping("kaipiao")
    public String kaipiao(long id,Model model,String code){
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
    	return "/background/goodsOrder/fapiao";
    }
    
    
    /**
     * 开发票
     * 张腾跃
     * 2016年10月19日15:06:48
     */
    @RequestMapping("fapiao")
    @ResponseBody
    public Json fapiao(Invoice invoice,long goodsId){
    	Json json  = new Json();
    	try {
    		invoice.setId(GenerateSequenceUtil.getUniqueId());
    		invoice.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    		invoiceService.saveInvoice(invoice);
    		try {
    			GoodsOrder goodsOrder = new GoodsOrder();
    			goodsOrder.setId(goodsId);
    			goodsOrder.setBilling(1);
    			goodsOrderService.updateByOrder(goodsOrder);
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
     * 修改订单状态
     * @param goodsOrder
     * @return
     */
    @RequestMapping("/updateGoodsOrder")
    @ResponseBody
    public Json updateGoodsOrder(GoodsOrder goodsOrder,Express express){
        ShopUser user=(ShopUser) webContext.getSessionUser();                   //获取当前登录商户
        Json json = new Json();
        UserAccountLog userAccountLog = new UserAccountLog();
        try {
            if (goodsOrder.getOrderState() == 9) {
            	//商户端扣钱
                UserAccount shopUserAccount = userAccountService.selectByUserId(user.getId());    //根据当前登录商户id查询余额
                BigDecimal b11 = new BigDecimal(shopUserAccount.getBalance());
                BigDecimal b22 = new BigDecimal(goodsOrder.getRealPrice());
                shopUserAccount.setUserId(goodsOrder.getUserId());
                shopUserAccount.setBalance((b11.subtract(b22)).doubleValue());                //从余额里面减去退款金额
                userAccountService.addBalanceByUserId(shopUserAccount);
                //客户端+钱
                UserAccount userAccount = userAccountService.selectByUserId(goodsOrder.getUserId());
                BigDecimal b1 = new BigDecimal(userAccount.getBalance());
                BigDecimal b2 = new BigDecimal(goodsOrder.getRealPrice());
                userAccount.setUserId(goodsOrder.getUserId());
                userAccount.setBalance((b1.add(b2)).doubleValue());
                userAccountService.addBalanceByUserId(userAccount);
                //保存记录信息
                userAccountLog.setName("订单退款");
                userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
                userAccountLog.setType(4);
                userAccountLog.setTradeIntegration(0);
                userAccountLog.setIntegration(0);
                userAccountLog.setPrice(goodsOrder.getRealPrice());
                userAccountLog.setBalance((b1.add(b2)).doubleValue());
                userAccountLog.setUserId(goodsOrder.getUserId());
                userAccountLog.setExtractType(0);
                userAccountLogService.insertMessage(userAccountLog);
            }
            if(goodsOrder.getOrderState() == 3){
            	express.setId(GenerateSequenceUtil.getUniqueId());
            	express.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            	express.setOrderCode(Long.parseLong(goodsOrder.getOrderCode()));
            	express.setUserId(goodsOrder.getUserId());
            	goodsOrderService.saveExpress(express);
            }
            goodsOrderService.updateByOrderCode(goodsOrder);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    @RequestMapping("/updateDeleverFee")
    @ResponseBody
    public Json updateDeleverFee(String orderCode,String deleverFee){
    	Json json = new Json();
    	try {
    		Map map = new HashMap();
    		map.put("deleverFee", deleverFee);
    		map.put("orderCode",orderCode);
    		goodsOrderService.updateDeleverFeeByOrderCode(map);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("操作失败！");
			json.setSuccess(false);
		}
    	return json;
    }
}
