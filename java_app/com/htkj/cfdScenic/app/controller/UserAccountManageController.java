package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.service.ConsumerUserService;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author wangfenglong
 * @date 2016/9/28 00289:25.
 */
@RequestMapping("/background/userAccountManage/")
@Controller
public class UserAccountManageController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private UserAccountLogService userAccountLogService;


    /**
     * 账户余额 主页面
     * @return
     */
    @RequestMapping("/toUserAccountManage")
    public String toUserAccountManage() {
        return "/background/userAccount/Manager";
    }

    /**
     * 余额 - 列表
     * @param page
     * @param nickName
     * @param mobileNo
     * @return
     */
    @RequestMapping("/getUserAccountList")
    @ResponseBody
    public DataGrid getUserAccountList(PagerForm page ,String nickName , String mobileNo){
        Map<String , Object> map = new HashedMap();
        map.put("nickName" , nickName);
        map.put("mobileNo" , mobileNo);
        return userAccountService.getUserAccountList(page.getPageRequest(map));
    }

    /**
     * 充值
     * @param id
     * @param addBalance
     * @return
     */
    @RequestMapping("/addBalance")
    @ResponseBody
    public Json addBalance(Long id , String addBalance , Long userId) {
        Json json = new Json();
        UserAccountLog userAccountLog = new UserAccountLog();
        try {
            UserAccount userAccount = userAccountService.selectBalance(id);
            BigDecimal b1 = new BigDecimal(userAccount.getBalance());
            BigDecimal b2 = new BigDecimal(addBalance);
            userAccount.setId(id);
            userAccount.setBalance((b1.add(b2)).doubleValue());
            userAccountService.addBalance(userAccount);

            userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
            userAccountLog.setType(0);
            userAccountLog.setTradeIntegration(0);
            userAccountLog.setIntegration(0);
            userAccountLog.setPrice(Double.parseDouble(addBalance));
            userAccountLog.setBalance((b1.add(b2)).doubleValue());
            userAccountLog.setUserId(userId);
            userAccountLog.setExtractType(0);
            userAccountLogService.insertMessage(userAccountLog);

        } catch (Exception e) {
        	e.printStackTrace();
        }

        return json;
    }

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public Json updateState(Long id , Integer state) {
        Json json = new Json();
        ConsumerUser consumerUser = new ConsumerUser();
        try {
            if (state == 0) {
                state = 1;
            } else {
                state = 0;
            }
            consumerUser.setId(id);
            consumerUser.setState(state);
            consumerUserService.updateState(consumerUser);
            json.setSuccess(true);
        } catch (Exception e) {
        	e.printStackTrace();
            json.setSuccess(false);
        }
        return json;
    }
}
