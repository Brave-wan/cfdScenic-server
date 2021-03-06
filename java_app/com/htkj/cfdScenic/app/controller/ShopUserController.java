package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.*;
import com.htkj.cfdScenic.app.service.*;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/shopUser")
public class ShopUserController extends BaseController {

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9" };

	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	SysVerificationService sysVerificationService;
	@Autowired
	ShopInformationService shopInformationService;
	@Autowired
	MyPurseService myPurseService;
	@Autowired
	private ConsumerUserService consumerUserService;

	/**
	 * 登入 post http://localhost:8080/cfdScenic/shopUser/login 返回状态信息 0登入成功 1登入失败
	 * 4用户名错误 5密码错误 返回参数： token userId
	 */
	@RequestMapping(value = "login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(ShopUser shopUser) {
		ResponseMsg msg = new ResponseMsg();
		String telPhone = shopUser.getTelPhone();
		ShopUser userMessage = shopUserService.selectByPhone(telPhone);
		try {
		if (userMessage == null) {
			msg.setHearder(4, "用户名错误");
		} else {
			MD5 getMD5 = new MD5();
			String md5userPassword = getMD5.GetMD5Code(shopUser.getPassWord());
			String password = userMessage.getPassWord();
			if (password.equalsIgnoreCase(md5userPassword)) {
					Map<String, Object> map = new HashMap<String, Object>();
					String uid = UUID.randomUUID().toString();
					map.put("token", uid);
					map.put("userId", userMessage.getId());
					if(userMessage.getShopInformationId()!=null)
					{
						ShopInformation shopInformation=shopInformationService.getInforMationByid(userMessage.getShopInformationId());
						if(shopInformation.getIsAudit()==0||shopInformation.getIsAudit()==2)
						{
							map.put("shopMessge", shopInformation);
							map.put("state", shopInformation.getIsAudit());//0正在审核2审核失败
						}else
						{
							map.put("shopMessge", shopInformation);
							map.put("state", shopInformation.getIsAudit());//1审核通过
//							map.put("shopInformation",shopInformation);
						}
					}else
					{
						map.put("state", 6);//没有提交审核信息
						map.put("shopMessge", new ShopInformation());
					}
					userMessage.setShopToken(uid);
					shopUserService.updateUUID(userMessage);
					msg.setHearder(0, "登录成功");
					msg.setData(map);
					webContext.setSessionUser(userMessage);
			} else {
				msg.setHearder(5, "密码错误");
			}

		}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "登入失败");
			return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		}
		return JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
	}

	/*
	 * 注册 post http://localhost:8080/cfdScenic/shopUser/register
	 * 把用户注册的信息存进数据库表里面 请求参数 telPhone;//电话 nickName;//昵称 passWord;//密码
	 *  0注册成功 1注册失败 4信息不能为空 5账号以经注册 6请发送验证码
	 * 7验证码输入错误，请重新输入
	 */
	@RequestMapping(value = "register", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String register(ShopUser user, String checkcode) {
		ResponseMsg msg = new ResponseMsg();
		if (user.getTelPhone() != null & user.getPassWord() != null
				& checkcode != null) // 判断是否为空
		{
			String telPhone = user.getTelPhone();
			ShopUser userMessage = shopUserService.selectByPhone(telPhone);// 判断是否存在
			if (userMessage == null) {
				SysVerification fr = sysVerificationService
						.getMessage(telPhone);
				if (fr != null) {
					if (checkcode.equals(fr.getVerification())) {
						try {
							Long userId = GenerateSequenceUtil.getUniqueId();
							user.setId(userId);
							user.setPassWord(MD5.GetMD5Code(user.getPassWord()));
							user.setShopToken(UUID.randomUUID().toString());
							user.setState(0);
							user.setBRID(3L);
							shopUserService.insertMessage(user);
							UserAccount userAccount=new UserAccount();
							userAccount.setBalance(0D);
							userAccount.setId(GenerateSequenceUtil.getUniqueId());
							userAccount.setIntegration(0);
							userAccount.setUserId(userId);
							myPurseService.insertUserAccount(userAccount);
							msg.setHearder(0, "注册成功");
						} catch (Exception e) {
							msg.setHearder(1, "注册失败");
						}
					} else {
						msg.setHearder(7, "验证码输入错误，请重新输入");
					}
				} else {
					msg.setHearder(6, "请发送验证码");
				}
			} else {
				msg.setHearder(5, "账号已经注册");
			}
		} else {
			msg.setHearder(4, "信息不能为空");
		}
		return JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
	}
	
	
	/*
	 * 注册 post http://localhost:8080/cfdScenic/shopUser/auditMessage
	 * 
	 * sex;//性别（0男 1女） age;//年龄 realName;//真实姓名 idCard;//身份证 holdCardImg;//手持证件照
	 * faceCardImg;//身份证正面照 backCardImg;//身份证反面照 name;//商家名称 private Long
	 * shopId;//商家类型 （1酒店，2特产 3饭店4，小吃） businessScope;//经营范围 private Long
	 * accountType;//账户类型 （0 对公，1个人） accountName;//账户名称 bankCard;//银行卡号
	 * accountBank;//开户行 private Integer isLicense;//是否有营业执照 licenseImg;//营业执照照片
	 * otherImg1;//其他证件照1 otherImg2;//其他证件照2 0注册成功 1注册失败 4信息不能为空 5账号以经注册 6请发送验证码
	 * 7验证码输入错误，请重新输入
	 */
	@RequestMapping(value = "auditMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String auditMessage(ShopInformation shopInformation) {
		ResponseMsg msg = new ResponseMsg();
		
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {
				try{
					
				Long temId = GenerateSequenceUtil.getUniqueId();
				shopInformation.setId(temId);
				shopInformation.setIsAudit(0);
				shopInformation.setState(0);
				shopInformation.setShopUserId(userId);
				shopInformation.setIsWifi(0);
				shopInformation.setIsYushi(0);
				shopInformation.setIsBlss(0);
				shopInformation.setIsMedia(0);
				shopInformation.setIsFood(0);
				shopInformationService.insertShopInformation(shopInformation);
				
				ShopUser shopUser=new ShopUser();
				shopUser.setId(userId);
				shopUser.setShopInformationId(temId);
				shopUserService.updateInformationId(shopUser);

				UserAccount userAccount = new UserAccount();
				userAccount.setBalance(0D);
				userAccount.setId(GenerateSequenceUtil.getUniqueId());
				userAccount.setIntegration(0);
				userAccount.setUserId(temId);
				myPurseService.insertUserAccount(userAccount);
				msg.setHearder(0, "申请成功，请等待审核！");
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setHearder(1, "error");
				}

			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}
	
	
	/*
	 * 注册 post http://localhost:8080/cfdScenic/shopUser/auditMessage
	 * 
	 * sex;//性别（0男 1女） age;//年龄 realName;//真实姓名 idCard;//身份证 holdCardImg;//手持证件照
	 * faceCardImg;//身份证正面照 backCardImg;//身份证反面照 name;//商家名称 private Long
	 * shopId;//商家类型 （1酒店，2特产 3饭店4，小吃） businessScope;//经营范围 private Long
	 * accountType;//账户类型 （0 对公，1个人） accountName;//账户名称 bankCard;//银行卡号
	 * accountBank;//开户行 private Integer isLicense;//是否有营业执照 licenseImg;//营业执照照片
	 * otherImg1;//其他证件照1 otherImg2;//其他证件照2 0注册成功 1注册失败 4信息不能为空 5账号以经注册 6请发送验证码
	 * 7验证码输入错误，请重新输入
	 */
	@RequestMapping(value = "updateInformation", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateInformation(ShopInformation shopInformation) {
		ResponseMsg msg = new ResponseMsg();
		
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {
				try{
				
				shopInformation.setIsAudit(0);
				shopInformation.setState(0);
				shopInformationService.updateInformation(shopInformation);
				msg.setHearder(0, "申请成功");
				msg.setData(0);
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setHearder(1, "error");
				}

			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}
	
	
	
	
	

	/*
	 * 获取验证码 post http://localhost:8080/cfdScenic/shopUser/checkCode 请求参数 phone
	 * //电话号码 返回状态 4信息不能为空
	 */
	@RequestMapping(value = "checkCode", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkCode(SysVerification sysVerification)
			throws UnsupportedEncodingException {
		ResponseMsg msg = new ResponseMsg();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int x = (int) (Math.random() * 10);
			str.append(strDigits[x]);
		}
		String phone = sysVerification.getPhone();
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
				msg.setHearder(1, map.get("data"));
			} else {
				msg.setHearder(Integer.parseInt(map.get("state")),
						map.get("data"));
			}
		} else {
			msg.setHearder(4, "信息不能为空");
		}

		return JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
	}
	
	
	
	
	/*
	 * 注册 post http://localhost:8080/cfdScenic/shopUser/auditMessage
	 * 
	 * sex;//性别（0男 1女） age;//年龄 realName;//真实姓名 idCard;//身份证 holdCardImg;//手持证件照
	 * faceCardImg;//身份证正面照 backCardImg;//身份证反面照 name;//商家名称 private Long
	 * shopId;//商家类型 （1酒店，2特产 3饭店4，小吃） businessScope;//经营范围 private Long
	 * accountType;//账户类型 （0 对公，1个人） accountName;//账户名称 bankCard;//银行卡号
	 * accountBank;//开户行 private Integer isLicense;//是否有营业执照 licenseImg;//营业执照照片
	 * otherImg1;//其他证件照1 otherImg2;//其他证件照2 0注册成功 1注册失败 4信息不能为空 5账号以经注册 6请发送验证码
	 * 7验证码输入错误，请重新输入
	 */
	@RequestMapping(value = "updateShopPsw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateShopPsw(String oldPassWord,String newPassWord) {
		ResponseMsg msg = new ResponseMsg();
		
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {
				try{
					
					ShopUser shopUser=shopUserService.selectByUserId(userId);
					if(shopUser.getPassWord().equals(MD5.GetMD5Code(oldPassWord)))
					{
						shopUser.setPassWord(MD5.GetMD5Code(newPassWord));
						shopUserService.update(shopUser);
						msg.setHearder(0, "修改成功");
					}else
					{
						msg.setHearder(4, "原密码错误");
					}
					
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setHearder(1, "error");
				}

			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}
	

	/**
	 * 找回密码 post http://localhost:8080/cfdScenic/shopUser/findPsw 请求参数: mobileNo
	 * 手机号 checkcode 验证码 password 新密码
	 */
	@RequestMapping(value = "findPsw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findPsw(ShopUser user, String checkcode) {
		ResponseMsg msg = new ResponseMsg();
		if (user.getTelPhone() != null & user.getPassWord() != null
				& checkcode != null) // 判断是否为空
		{
			String telPhone = user.getTelPhone();
			ShopUser userMessage = shopUserService.selectByPhone(telPhone);// 判断是否存在
			if (userMessage != null) {
				SysVerification fr = sysVerificationService
						.getMessage(telPhone);
				if (fr != null) {
					if (checkcode.equals(fr.getVerification())) {
						try {
							userMessage.setPassWord(MD5.GetMD5Code(user
									.getPassWord()));
							// userMessage.setTelPhone(telPhone);
							shopUserService.update(userMessage);
							msg.setHearder(0, "修改成功");
						} catch (Exception e) {
							msg.setHearder(1, "修改失败");
						}
					} else {
						msg.setHearder(7, "验证码输入错误，请重新输入");
					}
				} else {
					msg.setHearder(6, "请发送验证码");
				}
			} else {
				msg.setHearder(5, "账号不存在");
			}
		} else {
			msg.setHearder(4, "信息不能为空");
		}
		return JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 店铺信息-商家认证信息 post http://localhost:8080/cfdScenic/shopUser/shopUserMessage
	 * 根据商户id来查找商户的认证信息 请求参数:
	 * 
	 */
	@RequestMapping(value = "shopUserMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopUserMessage() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {

				Map<String, Object> map = new HashMap<String, Object>();
				try {
					map = shopUserService.shopUserMessage(userId);
					msg.setHearder(0, "success");
					msg.setData(map);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}

	/**
	 * 店铺信息-实名认证信息 post
	 * http://localhost:8080/cfdScenic/shopUser/shopAutonymMessage
	 * 根据商户id来查找商户的认证信息 请求参数: id //商户id
	 */
	@RequestMapping(value = "shopAutonymMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopAutonymMessage() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();

		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				try {
					map = shopUserService.shopAutonymMessage(userId);
					msg.setHearder(0, "success");
					msg.setData(map);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}

	/**
	 * 店铺信息-店铺信息 post http://localhost:8080/cfdScenic/shopUser/storeMessage
	 * 根据类型来查数据库 请求参数: status //店铺信息 1(酒店)2(饭店)3(商品)
	 */
	@RequestMapping(value = "storeMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String storeMessage(Integer status) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();

		String token = webContext.getRequest().getHeader("Authorization");
		if (token != null) {
			Long userId = shopUserService.getShopUserIdByToken(token);
			if (userId != null) {

				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> content = new HashMap<String, Object>();
				try {
					switch (status) {
					case 1:
						map.put("status", "1");
						map.put("userId", userId);
						break;
					case 2:
						map.put("status", "2");
						map.put("userId", userId);
						break;
					case 3:
						map.put("status", "3,4");
						map.put("userId", userId);
						break;
					}
					content = shopInformationService.storeMessage(map);
					msg.setHearder(0, "success");
					msg.setData(content);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			} else {
				msg.setHearder(3, "异地登陆");
			}
		} else {
			msg.setHearder(2, "token为空");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		return json;
	}
	
	
	/**
	 * 我的钱包 - 余额
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/myBalance?type=0
	 * 参数  type 类型（0余额交易1积分交易2结算交易3,商户给用户充值4,充值余额）
	 * 流程
	 * 1，通过userid获取余额
	 * 2，通过userid获取交易记录明细
	 * 调用表
	 * user_account
	 * user_account_log
	 */
	@RequestMapping(value = "/myBalance", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myPurseDetail() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					map.put("userId",userId);
					Map balanceMap = new HashMap();
					balanceMap = myPurseService.getMyBalance(userId);
					List<Map<String,Object>> tradeLogList = new ArrayList<Map<String,Object>>();
					tradeLogList = myPurseService.getShopTradeLog(map);
					Map returnMap = new HashMap();
					returnMap.put("balanceMap",balanceMap);
					if(tradeLogList.size()>0&&tradeLogList.get(0)!=null)
					{
						returnMap.put("tradeLogList",tradeLogList);
					}else
					{
						returnMap.put("tradeLogList",new ArrayList());
					}
					msg.setData(returnMap);
					msg.setHearder(0, "ok");

				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 我的钱包 - 提现 - 密码判断
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/pwWithdraw?passWord=123456&balance=30
	 * 流程
	 * 1，先判断密码，成功之后就插入一条提现记录
	 * 2，更新自己账户的余额
	 * 调用表
	 */
	@RequestMapping(value = "/pwWithdraw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String pwWithdraw(String passWord,Double balance) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
			//		String payPassWord = myPurseService.getPayPassWord(userId);
					Map balanceMap = myPurseService.getMyBalance(userId);
					ShopUser shopUser = shopUserService.selectByUserId(userId);
					ShopInformation shopInformation=shopInformationService.getInforMationByid(shopUser.getShopInformationId());

					MD5 getMD5 = new MD5();
					if(getMD5.GetMD5Code(passWord).equals(shopInformation.getCashPassWord()))
					{
						Double userBalance = Double.parseDouble((balanceMap.get("balance"))+"");
						if(userBalance.compareTo(balance)>0)
						{
							//插入交易记录
							UserAccountLog userAccountLog = new UserAccountLog();
							userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
							userAccountLog.setPrice(balance);
							userAccountLog.setBalance(userBalance);
							userAccountLog.setCreateTime(sdf.format(new Date()));
							userAccountLog.setName("提现到"+shopInformation.getAccountName());
							userAccountLog.setType(1);
							userAccountLog.setTradeIntegration(0);
							userAccountLog.setIntegration(0);
							userAccountLog.setUserId(0l);
							userAccountLog.setShopId(shopInformation.getId());
							userAccountLog.setExtractType(0);
							myPurseService.saveUserAccountLog(userAccountLog);
							//更新余额  减操作
							Map map = new HashMap();
							map.put("balance",userBalance-balance);
							map.put("userId",userId);
							String integrationStr = (balance+"").substring(0,(balance+"").indexOf("."));
							map.put("integration",integrationStr);
							int type = 1;
							myPurseService.updateBalanceUserAccount(map,type);
							msg.setHearder(0,"ok");
						}else
						{
							msg.setHearder(5,"提现金额不能大于剩余金额");
						}
					}else
					{
						msg.setHearder(4,"提现密码错误");
					}

				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 我的消息
	 * http://localhost:8080/cfdScenic/shopUser/myMessage?&userType=0&page=页数&rows=条数
	 * 
	 * 调用表
	 */
	@RequestMapping(value = "/myMessage", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myMessage(PagerForm page,Integer userType) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = 0L;
				switch (userType) {
				case 0://商户
					userId = shopUserService.getShopUserIdByToken(token);	
					break;
				case 1://用户
					userId = consumerUserService.getUserIdByToken(token);
					break;
				}
				if (userId != null && userId!= 0) {
					//查询条件
					Map para = new HashMap();
					para.put("page",page.getPage());
					para.put("rows",page.getRows());
					para.put("userId",userId);
					//查询
					DataGrid list = shopUserService.getMyMessage(page.getPageRequest(para));
					msg.setHearder(0,"ok");
					msg.setData(list);
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 我的消息详情
	 * http://localhost:8080/cfdScenic/shopUser/myMessageDetail?detailId
	 * 
	 * 调用表
	 */
	@RequestMapping(value = "/myMessageDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myMessageDetail(Long detailId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String url = shopUserService.getDetailUrlbyId(detailId);
			msg.setHearder(0,"ok");
			msg.setData(url);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * shopUser/getAboutUs
	 * 关于我们
	 * http://localhost:8080/cfdScenic/shopUser/myMessage?siId=店铺ID&orderCode=订单号&page=页数&rows=条数
	 * 
	 * 调用表
	 */
	@RequestMapping(value = "/getAboutUs", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAboutUs() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String url = shopUserService.getAboutUs();
			msg.setData(url);
			msg.setHearder(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 获取支付宝信息
	 */
	@RequestMapping("getAlipayInfo")
	@ResponseBody
	public String getAlipayInfo(Long siId){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				
					Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null && userId!= 0) {
					AlipayInfo ai = shopUserService.getAlipayInfoBySiId(siId);
					msg.setHearder(0,"ok");
					msg.setData(ai);
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	

}
