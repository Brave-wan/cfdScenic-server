package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.service.ConsumerUserService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户管理
 * @author wangfenglong
 * @date 2016/10/19 001910:58.
 */
@RequestMapping("/background/consumerUserManage")
@Controller
public class ConsumerUserManageController extends BaseController {

    @Autowired
    private ConsumerUserService consumerUserService;

    /**
     * 用户管理主页
     * @return
     */
    @RequestMapping("/toConsumerUserManage")
    public String toConsumerUserManage(){
        return "/background/consumerUser/Manager";
    }

    /**
     * 用户管理 - 列表
     * @param page
     * @param consumerUser
     * @return
     */
    @RequestMapping("/getConsumerUserList")
    @ResponseBody
    public DataGrid getConsumerUserList(PagerForm page , ConsumerUser consumerUser){
        return consumerUserService.getConsumerUserList(page.getPageRequest(consumerUser.toMap()));
    }

    @RequestMapping("/updateState")
    @ResponseBody
    public Json updateState(ConsumerUser consumerUser) {
        Json json = new Json();
        consumerUserService.updateState(consumerUser);
        json.setSuccess(true);
        return json;
    }
}
