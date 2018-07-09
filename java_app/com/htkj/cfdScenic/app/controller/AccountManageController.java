package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.service.*;
import com.htrj.common.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author wangfenglong
 * @date 2016/10/20 002010:17.
 */
@RequestMapping("/background/accountManage")
@Controller
public class AccountManageController extends BaseController {

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

    @RequestMapping("/toAccountManage")
    public String toAccountManage(ModelMap model){
        ShopUser user = (ShopUser) webContext.getSessionShopUser();
        try {
            if (user != null){
                Map<String, Object> shopInformation = shopInformationService.selectByPrimaryKey(user.getShopInformationId());
                Integer shopId =Integer.parseInt(shopInformation.get("shop_id").toString());
                if (shopId == 1) {     //酒店
                    Double todayTurnover = hotelOrderService.getTodayTurnover((Long) shopInformation.get("id"));    //今日营业额
                    Double turnover = hotelOrderService.getTurnover((Long) shopInformation.get("id"));              //总营业额
                    model.put("todayTurnover",todayTurnover);
                    model.put("turnover",turnover);
                } else if (shopId == 2) {     //饭店
                    Double todayTurnover = restaurantOrderService.getTodayTurnover((Long) shopInformation.get("id"));    //今日营业额
                    Double turnover = restaurantOrderService.getTurnover((Long) shopInformation.get("id"));              //总营业额
                    model.put("todayTurnover",todayTurnover);
                    model.put("turnover",turnover);
                } else {                                     //特产、小吃
                    Double todayTurnover = goodsOrderService.getTodayTurnover((Long) shopInformation.get("id"));    //今日营业额
                    Double turnover = goodsOrderService.getTurnover((Long) shopInformation.get("id"));              //总营业额
                    model.put("todayTurnover",todayTurnover);
                    model.put("turnover",turnover);
                }
            } else {            //PC端
                Double todayTurnover = visitorsOrderService.getTodayTurnover();    //今日营业额
                Double turnover = visitorsOrderService.getTurnover();              //总营业额
                model.put("todayTurnover",todayTurnover);
                model.put("turnover",turnover);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return "/background/account/Manager";
    }


}
