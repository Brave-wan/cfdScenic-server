package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.Invoice;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htkj.cfdScenic.app.service.InvoiceService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.service.VisitorsOrderService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @date 2016/9/30 00309:27.
 */
@RequestMapping("/background/visitorsOrderManage")
@Controller
public class VisitorsOrderManageController extends BaseController {


    @Autowired
    private VisitorsOrderService visitorsOrderService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private UserAccountLogService userAccountLogService;
    @Autowired
    private InvoiceService invoiceService;

    /**
     * 订单首页
     * @return
     */
    @RequestMapping("/toVisitorsOrderManage")
    public String toVisitorsOrderManage() {
        return "/background/visitorsOrder/Manager";
    }


    /**
     * 订单 - 列表
     * @param page
     * @param visitorsOrder
     * @param nickName
     * @return
     */
    @RequestMapping("/getVisitorsOrderList")
    @ResponseBody
    public DataGrid getVisitorsOrderList(PagerForm page , VisitorsOrder visitorsOrder , String nickName){
        Map<String , Object> map = new HashMap();
        map.put("orderCode",visitorsOrder.getOrderCode());
        map.put("createTime",visitorsOrder.getCreateTime());
        map.put("orderState",visitorsOrder.getOrderState());
        map.put("nickName",nickName);
        return visitorsOrderService.getVisitorsOrderList(page.getPageRequest(map));
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
            Map<String, Object> map = visitorsOrderService.selectByPrimaryKey(id);
            model.addAttribute("model", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/background/visitorsOrder/ShowPage";
    }


    /**
     * 订单 - 删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteVisitorsOrder")
    @ResponseBody
    public Json deleteVisitorsOrder(Long id){
        Json json = new Json();
        try {
            visitorsOrderService.deleteVisitorsOrder(id);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 修改订单状态
     * @param visitorsOrder
     * @return
     */
    @RequestMapping("/updateVisitorsOrder")
    @ResponseBody
    public Json updateVisitorsOrder(VisitorsOrder visitorsOrder){
        Json json = new Json();
        UserAccountLog userAccountLog = new UserAccountLog();
        try {
            if (visitorsOrder.getOrderState() == 7) {
                UserAccount userAccount = userAccountService.selectByUserId(visitorsOrder.getUserId());
                BigDecimal b1 = new BigDecimal(userAccount.getBalance());
                BigDecimal b2 = new BigDecimal(visitorsOrder.getRealPrice());
                userAccount.setUserId(visitorsOrder.getUserId());
                userAccount.setBalance((b1.add(b2)).doubleValue());
                userAccountService.addBalanceByUserId(userAccount);

                userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
                userAccountLog.setType(4);
                userAccountLog.setTradeIntegration(0);
                userAccountLog.setIntegration(0);
                userAccountLog.setPrice(visitorsOrder.getRealPrice());
                userAccountLog.setBalance((b1.add(b2)).doubleValue());
                userAccountLog.setUserId(visitorsOrder.getUserId());
                userAccountLog.setExtractType(0);
                userAccountLogService.insertMessage(userAccountLog);
            }

            visitorsOrderService.updateVisitorsOrder(visitorsOrder);
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 景点订单开票 - 主页
     * @return
     */
    @RequestMapping("/toInvoiceList")
    public String toInvoiceList(){
        return "/background/visitorsOrder/invoiceList";
    }

    /**
     * 景点订单开票 - 列表
     * @param pagerForm
     * @param visitorsOrder
     * @param nickName
     * @return
     */
    @RequestMapping("/getInvoiceList")
    @ResponseBody
    public DataGrid getInvoiceList(PagerForm pagerForm , VisitorsOrder visitorsOrder , String nickName){
        Map<String , Object> map = new HashMap();
        map.put("orderCode",visitorsOrder.getOrderCode());
        map.put("nickName",nickName);
        map.put("createTime",visitorsOrder.getCreateTime());
        return visitorsOrderService.getInvoiceList(pagerForm.getPageRequest(map));
    }

    /**
     * Title:开发票
     * @author wangfenglong
     * @date 2016年9月6日
     */
    @RequestMapping("/changeInvoice")
    @ResponseBody
    public Json changeInvoice(Invoice invoice , String orderCodes , String linkIds){
        Json json = new Json();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            String[] orderCode = orderCodes.split(",");
            String[] linkId = linkIds.split(",");
            for (int i = 0 ; i < orderCode.length ; i ++) {
                VisitorsOrder visitorsOrder = visitorsOrderService.selectByOrderCode(orderCode[i]);
                //if(visitorsOrder.getIsInvoice() == 0){
                visitorsOrder.setOrderCode(orderCode[i]);
                visitorsOrder.setInvoiceState(1);
                visitorsOrder.setIsInvoice(1);
                visitorsOrderService.updateVisitorsOrder(visitorsOrder);

                invoice.setId(GenerateSequenceUtil.getUniqueId());
                invoice.setInvoiceTime(now);
                invoice.setInvoiceState(2);
                invoice.setOrderCode(orderCode[i]);
                invoice.setLinkId(Long.parseLong(linkId[i]));
                invoice.setCreateTime(now);
                invoiceService.insertSelective(invoice);
                // } else {
                //  json.setCode(0);
                // }
                // json.setCode(1);
            }
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            json.setMessage("开发票失败！");
        }

        return json;
    }
}
