package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.WithdrawalApply;
import com.htkj.cfdScenic.app.service.UserAccountLogService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htkj.cfdScenic.app.service.WithdrawalApplyService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 
* <p>Title: UserAccountLogManageController</p>
* <p>Description: </p>
* @author Administrator
* @date 2016年10月19日上午10:52:42
 */
@RequestMapping("/background/withdrawalApplyManage/")
@Controller
public class WithdrawalApplyManageController extends BaseController {

    @Autowired
    private WithdrawalApplyService withdrawalApplyService;
    
    @Autowired
    private UserAccountLogService userAccountLogService;
    
    @Autowired
    private UserAccountService userAccountService;


    /**
     * 主页面
     * @return
     */
    @RequestMapping("/toWithdrawalApplyManage")
    public String towithdrawalApplyManage() {
        return "/background/withdrawalApply/Manager";
    }

    /**
     * 列表
     * @param page
     * @param nickName
     * @param mobileNo
     * @return
     */
    @RequestMapping("/getWithdrawalApplyList")
    @ResponseBody
    public DataGrid getUserAccountLogList(PagerForm page ,String name,String phone,String createTime){
    
		Map<String , Object> map = new HashedMap();
        map.put("nickName" , name);
        map.put("mobileNo" , phone);
        map.put("createTime" , createTime);
        return withdrawalApplyService.getWithdrawalApplyManage(page.getPageRequest(map));
    }

    /**
     * 修改审核状态
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public Json updateState(Long id , Long uid ,Integer state,String wdaname,Double balance,Double beginBalance) {
    	Json json = new Json();
		try {
			WithdrawalApply wa = new WithdrawalApply();
			wa.setId(id);
			wa.setState(state);
			wa.setAuditTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			//更新审核表状态
			withdrawalApplyService.updateByPrimaryKeySelective(wa);
			
			//在log表添加一条数据
			UserAccountLog userAccountLog = new UserAccountLog();
			userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
			userAccountLog.setName(wdaname);
			userAccountLog.setType(1);
			userAccountLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			userAccountLog.setPrice(balance);
			userAccountLog.setExtractType(state);
			userAccountLog.setUserId(uid);
			userAccountLogService.insertMessage(userAccountLog);
			
			//审核未通过，更新用户的余额
			if(state ==2){
				UserAccount userAccount = new UserAccount();
				userAccount.setUserId(uid);
				userAccount.setBalance(beginBalance);
				userAccountService.addBalanceByUserId(userAccount);
			}
				
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
    }
    
}
