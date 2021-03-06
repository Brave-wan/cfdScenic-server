package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.SmsSendRecord;
import com.htkj.cfdScenic.app.model.SysVerification;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.service.MyPurseService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.SmsSendService;
import com.htkj.cfdScenic.app.service.SysVerificationService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.resources.CommonStrings;
import com.htrj.common.utils.CalendarHelper;
import com.htrj.common.utils.GenerateSequenceUtil;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author wangfenglong
 * @date 2016/9/29 002910:37.
 */
@Controller
@RequestMapping("/ShopUserPcController")
public class ShopUserPcController extends BaseController {
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9" };
    @Autowired
    private ShopUserService shopUserService;

    @Autowired
    private SysVerificationService sysVerificationService;
    @Autowired
	private SmsSendService smsSendService;
    @Autowired
    private MyPurseService myPurseService;
    @Autowired
    private ShopInformationService informationService;
    
    
    
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "/test/testLogin";
    }

    /**
     * 商家 PC 登录
     * @param telephone
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/tologin")
    public String login(String telPhone,String passWord,ModelMap model){
        String view;
        ShopUser user = null;
        if (webContext.isAuthorized()) {
            user = (ShopUser) webContext.getSessionShopUser();
            view = CommonStrings.VIEW_PC_INDEX;
        } else {
            try {
                user = shopUserService.login(telPhone,passWord);
                if (user != null) {
                	//判断店铺是否审核
                	ShopInformation information= informationService.selectByShopUserPrimaryKey(user.getId());
                	if(information!=null){
                		if(information.getState()==0){
                			if(information.getIsAudit()==1){
                				webContext.setSessionShopUser(user);
                				view = CommonStrings.VIEW_PC_INDEX;
                			}else if(information.getIsAudit()==2){
                				webContext.setSessionShopUser(user);
                				return "/home/regist-smrz";
                			}else{
                				view = CommonStrings.VIEW_PC_LOGIN;
                				model.put("showMsg","您的店铺正在审核中，稍后在进行登录");
                			}
                		}else if(information.getState()==1){
                			 view = CommonStrings.VIEW_PC_LOGIN;
                             model.put("showMsg","用户已被禁用！");
                		}else{
                			 view = CommonStrings.VIEW_PC_LOGIN;
                             model.put("showMsg","用户已被删除！");
                		}
                	}else{
                		return "/home/regist-smrz";
                	}
                } else {
                    view = CommonStrings.VIEW_PC_LOGIN;
                    model.put("showMsg","用户不存在！");
                }
            } catch (BusinessException e) {              
            	model.put("showMsg" , e.getMessage());
                view = CommonStrings.VIEW_PC_LOGIN;
            }
        }
        if (view.equals(CommonStrings.VIEW_PC_INDEX)) {
            model.put("user" , user);
        }

        return view;
    }
    @RequestMapping("login")
    public String tologin(){
    	return "/home/shoplogin";
    }
    //注册后进行认证
    @RequestMapping("/registsmrz")
    public String registsmrz(){
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();
    	return "/home/regist-smrz";
    }
/**
 * 跳转至注册页面
 * 张腾跃
 * 2016年10月9日09:17:24
 */
    @RequestMapping("toregister")
    public String toregister(){
    	return "/home/regitser";
    }
    /**
     * 注册
     * @param shopUser
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Json register(ShopUser shopUser , String code ) {
        Json json = new Json();
        try {
            SysVerification message = sysVerificationService.getMessage(shopUser.getTelPhone());
            if (message != null) {
                if (!(message.getVerification().equals(code))) {
                    json.setSuccess(true);
                    json.setCode(0); //验证码错误
                } else {
                    String password = new Md5Hash(shopUser.getPassWord()).toHex();
                    shopUser.setPassWord(password);
                    shopUser.setId(GenerateSequenceUtil.getUniqueId());
                    shopUser.setCreateTime(CalendarHelper.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    shopUser.setState(0);//0:正常  1:停用  2:删除
                    shopUserService.register(shopUser);
                    json.setSuccess(true);
                    json.setCode(1); //注册成功
                }
            } else {
                json.setSuccess(false);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return json;
    }


    /**
     * 判断手机号是否已经被注册
     * @param telphone
     * @return
     */
    @RequestMapping("/isExist")
    @ResponseBody
    public Json isExist(String telphone) {
        Json json = new Json();
        try {
            Boolean flag = shopUserService.selectByTelPhone(telphone);
            json.setSuccess(flag);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return json;
    }
    /**
     * 忘记密码
     * 跳转忘记密码找回页面
     * 张腾跃
     * 2016年10月8日18:05:13
     */
    @RequestMapping("toretrieve")
    public String toretrieve(){
    	return "/home/forget-mm";
    }
    /**
     * 发送验证码
     * 张腾跃
     * 2016年10月8日19:05:34
     */
    @RequestMapping("fsyzm")
    @ResponseBody
    public Json checkCode(SysVerification sysVerification,String phone)
			throws UnsupportedEncodingException {
    	ShopUser user = shopUserService.selectByPhone(phone);// 判断是否存在
    	Json json = new Json();
    	if(user==null){
    		json.setSuccess(false);
    		json.setMessage("用户不存在");
    	}else{
    		try {
    			ShopInformation shopInformation = informationService.selectByShopUserPrimaryKey(user.getId());
    			if(shopInformation!=null){
    				Integer state = shopInformation.getState();
    				if(state==0){
    					StringBuffer str = new StringBuffer();
    					for (int i = 0; i < 6; i++) {
    						int x = (int) (Math.random() * 10);
    						str.append(strDigits[x]);
    					}
    					if (phone != null) {
    						SmsSendRecord sms = new SmsSendRecord();
    						sms.setMobiles(phone.toString());
    						sms.setNeedstatus(false);
    						sms.setContent(str.toString());
    						Map<String, String> map = smsSendService.SmsSend(sms);
    						if (map.get("state").equals("0")) {
    							SysVerification message = sysVerificationService
    									.getMessage(phone);
    							if (message == null) {
    								sysVerification.setId(GenerateSequenceUtil.getUniqueId());
    								sysVerification.setVerification(str.toString());
    								sysVerification.setCreateTime(new Date().toString());
    								sysVerification.setPhone(phone);
    								sysVerificationService.insertMessage(sysVerification);
    							} else {
    								message.setVerification(str.toString());
    								sysVerificationService.updateMessage(message);
    							}
    							json.setSuccess(true);
    						} else {
    							json.setSuccess(false);
    						}
    					}
    				}else if(state ==1){
    					json.setSuccess(false);
    					json.setMessage("该账号已被禁用");
    				}else{
    					json.setSuccess(false);
    					json.setMessage("该账号已被删除");
    				}
    			}else{
					StringBuffer str = new StringBuffer();
					for (int i = 0; i < 6; i++) {
						int x = (int) (Math.random() * 10);
						str.append(strDigits[x]);
					}
					if (phone != null) {
						SmsSendRecord sms = new SmsSendRecord();
						sms.setMobiles(phone.toString());
						sms.setNeedstatus(false);
						sms.setContent(str.toString());
						Map<String, String> map = smsSendService.SmsSend(sms);
						if (map.get("state").equals("0")) {
							SysVerification message = sysVerificationService
									.getMessage(phone);
							if (message == null) {
								sysVerification.setId(GenerateSequenceUtil.getUniqueId());
								sysVerification.setVerification(str.toString());
								sysVerification.setCreateTime(new Date().toString());
								sysVerification.setPhone(phone);
								sysVerificationService.insertMessage(sysVerification);
							} else {
								message.setVerification(str.toString());
								sysVerificationService.updateMessage(message);
							}
							json.setSuccess(true);
						} else {
							json.setSuccess(false);
						}
					}
    			}
    		} catch (Exception e) {
    			e.printStackTrace();
			}
    	}
		return json;
	}
    
   /**
    * 修改密碼
    *  张腾跃
    *  2016年10月9日10:17:34
    * @param user
    * @param yzm
    * @return
    */
    @RequestMapping("zhmm")
    @ResponseBody
    public Json zhmm(ShopUser user,String yzm){
    	Json json = new Json();
			String telPhone = user.getTelPhone();
			ShopUser userMessage = shopUserService.selectByPhone(telPhone);// 判断是否存在
			if (userMessage != null) {
				SysVerification fr = sysVerificationService
						.getMessage(telPhone);
				if (fr != null) {
					if (yzm.equals(fr.getVerification())) {
						try {
							user.setPassWord(MD5.GetMD5Code(user.getPassWord()));
							user.setTelPhone(telPhone);
							shopUserService.update(user);
							json.setSuccess(true);
						} catch (Exception e) {
							json.setSuccess(false);
							json.setMessage("找回密码失败");
						}
					} else {
						json.setSuccess(false);
						json.setMessage("验证码输入错误，请重新输入");
					}
				} else {
					json.setSuccess(false);
					json.setMessage("请发送验证码");
				}
			} else {
				json.setSuccess(false);
				json.setMessage("用户不存在");
			}
    	
    	return json;
    }
    /**
     * 注册用验证码
     * 2016年10月9日09:57:54
     * 张腾跃
     */
    @RequestMapping("zcyzm")
    @ResponseBody
    public Json zcyzm(SysVerification sysVerification,String phone)
			throws UnsupportedEncodingException {
    	ShopUser user = shopUserService.selectByPhone(phone);// 判断是否存在
    	Json json = new Json();
    	if(user!=null){
    		json.setSuccess(false);
    		json.setMessage("用户已存在");
    	}else{
			StringBuffer str = new StringBuffer();
			for (int i = 0; i < 6; i++) {
				int x = (int) (Math.random() * 10);
				str.append(strDigits[x]);
			}
			if (phone != null) {
				SmsSendRecord sms = new SmsSendRecord();
				sms.setMobiles(phone.toString());
				sms.setNeedstatus(false);
				sms.setContent(str.toString());
				Map<String, String> map = smsSendService.SmsSend(sms);
				if (map.get("state").equals("0")) {
					SysVerification message = sysVerificationService
							.getMessage(phone);
					if (message == null) {
						sysVerification.setId(GenerateSequenceUtil.getUniqueId());
						sysVerification.setVerification(str.toString());
						sysVerification.setCreateTime(new Date().toString());
						sysVerification.setPhone(phone);
						sysVerificationService.insertMessage(sysVerification);
					} else {
						message.setVerification(str.toString());
						sysVerificationService.updateMessage(message);
					}
					json.setSuccess(true);
				} else {
					json.setSuccess(false);
				}
			}
    	}
		return json;
	}
    /**
     * 商家注册
     * 张腾跃
     * 2016年10月9日10:23:52
     * @param user
     * @param yzm
     * @return
     */
    @RequestMapping("zhuce")
    @ResponseBody
    public Json zhuce(ShopUser user,String yzm){
    	Json json = new Json();
			String telPhone = user.getTelPhone();
			ShopUser userMessage = shopUserService.selectByPhone(telPhone);// 判断是否存在
			if (userMessage == null) {
				SysVerification fr = sysVerificationService
						.getMessage(telPhone);
				if (fr != null) {
					if (yzm.equals(fr.getVerification())) {
						try {
							Long userId = GenerateSequenceUtil.getUniqueId();
							user.setId(userId);
							user.setPassWord(MD5.GetMD5Code(user.getPassWord()));
							user.setShopToken(UUID.randomUUID().toString());
							user.setState(0);
							user.setTelPhone(telPhone);
							user.setBRID(3L);
							shopUserService.insertMessage(user);
							UserAccount userAccount=new UserAccount();
							userAccount.setBalance(0D);
							userAccount.setId(GenerateSequenceUtil.getUniqueId());
							userAccount.setIntegration(0);
							userAccount.setUserId(userId);
							myPurseService.insertUserAccount(userAccount);
							webContext.setSessionShopUser(user);
							json.setSuccess(true);
						} catch (Exception e) {
							json.setSuccess(false);
							json.setMessage("注册失败");
						}
					} else {
						json.setSuccess(false);
						json.setMessage("验证码输入错误，请重新输入");
					}
				} else {
					json.setSuccess(false);
					json.setMessage("请发送验证码");
				}
			} else {
				json.setSuccess(false);
				json.setMessage("用户已存在");
			}
    	
    	return json;
    }
    /**
     * 修改密码
     * 张腾跃
     * 2016年10月12日15:31:46
     */
    @RequestMapping("touppwd")
    public String touppwd(){
    	return "/home/uppwd";
    }
    @RequestMapping("getByid")
    @ResponseBody
	public DataGrid getByid(PagerForm page,ShopUser shopUser){
		ShopUser user = (ShopUser) webContext.getSessionShopUser();
		shopUser.setId(user.getId());
		DataGrid list = shopUserService.getByid(page.getPageRequest(shopUser.toMap()));
		return list;
	}
    
    @RequestMapping("uppwd")
    @ResponseBody
    public Json uppwd(ShopUser shopUser){
    	Json json = new Json();
    	ShopUser user = (ShopUser) webContext.getSessionShopUser();
    	String jiu = user.getPassWord();
    	String shujiu = new Md5Hash(shopUser.getRealName()).toHex();
    	if(jiu.equals(shujiu)){
    		String pwd = new Md5Hash(shopUser.getPassWord()).toHex();	
    		shopUser.setPassWord(pwd);
    		shopUser.setTelPhone(user.getTelPhone());
    		try {
    			shopUserService.update(shopUser);
    			json.setSuccess(true);
			} catch (Exception e) {
				// TODO: handle exception
				json.setSuccess(false);
				json.setMessage("系统错误");
			}
    	}else{
    		json.setMessage("旧密码不正确，请重新输入");
    		json.setSuccess(false);
    	}
    	return json;
    }
}
