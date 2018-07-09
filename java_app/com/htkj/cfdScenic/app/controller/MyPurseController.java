package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.SysVerification;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.UserBank;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htkj.cfdScenic.app.model.WithdrawalApply;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.MyPurseService;
import com.htkj.cfdScenic.app.service.SysVerificationService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("interFace/MyPurse")
public class MyPurseController extends BaseController{

	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private MyPurseService myPurseService;
	@Autowired
	private SysVerificationService sysVerificationService;
	
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
	public String myPurseDetail(Integer type) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					map.put("type",type);
					map.put("userId",userId);
					Map balanceMap = new HashMap();
					balanceMap = myPurseService.getMyBalance(userId);
					if(balanceMap != null){
						List<Map<String,Object>> tradeLogList = new ArrayList<Map<String,Object>>();
						tradeLogList = myPurseService.getTradeLog(map);
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
					}else{
						UserAccount userAccount = new UserAccount();
						userAccount.setId(GenerateSequenceUtil.getUniqueId());
						userAccount.setUserId(userId);
						userAccount.setBalance(0D);
						userAccount.setIntegration(0);
						myPurseService.insertUserAccount(userAccount);
						Map returnMap = new HashMap();
						returnMap.put("balanceMap",0);
						returnMap.put("tradeLogList",new ArrayList());
						msg.setData(returnMap);
						msg.setHearder(0, "ok");
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
	 * 我的钱包 - 充值
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/recharge?passWord=123456&balance=20&name=测试订单
	 * 参数：
	 * passWork（用户输入密码） balance（充值金额）
	 * 流程
	 * 1，查询出用户的支付密码
	 * 2，判断支付密码，正确，插入一条交易记录，更近用户的余额，错误，返回状态码
	 * 
	 * 
	 */
	@RequestMapping(value = "/recharge", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String recharge(Double balance,String name) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map balanceMap = myPurseService.getMyBalance(userId);
							Double userBalance = Double.parseDouble((balanceMap.get("balance"))+"");
							// 插入交易记录
							UserAccountLog userAccountLog = new UserAccountLog();
							userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
							userAccountLog.setPrice(balance);
							userAccountLog.setBalance(balance + userBalance);
							userAccountLog.setCreateTime(sdf.format(new Date()));
							userAccountLog.setName(name);
							userAccountLog.setType(0);
							userAccountLog.setTradeIntegration(0);
							userAccountLog.setIntegration(0);
							userAccountLog.setUserId(userId);
							userAccountLog.setShopId(0L);
							userAccountLog.setExtractType(0);
							myPurseService.saveUserAccountLog(userAccountLog);
							// 更新余额
							Map map = new HashMap();
							map.put("balance", userBalance+balance);
							map.put("userId", userId);
							int type = 0;
							myPurseService.updateBalanceUserAccount(map, type);
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
	 * 我的钱包 - 提现 - 回显(银行卡，余额)
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/withdraw
	 * 流程
	 * 1，提现之前先回显数据 (银行卡  ，余额)
	 */
	@RequestMapping(value = "/withdraw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String withdraw() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List bankList = myPurseService.getBank(userId);
					Map myBalance = myPurseService.getMyBalance(userId);
					Map map = new HashMap();
					if(bankList.size()>0&&bankList.get(0)!=null)
					{
						map.put("bank", bankList);
					}else
					{
						map.put("bank", new ArrayList());
					}
					map.put("balance", myBalance);
					msg.setData(map);
					msg.setHearder(0,"ok");
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
		 * http://localhost:8080/cfdScenic/interFace/MyPurse/isPassWord?passWord=123456&balance=30&name=提现到中国建设银行
		 * 流程
		 * 1，先判断密码，成功之后就插入一条提现记录
		 * 2，更新自己账户的余额
		 * 调用表
		 */
		@RequestMapping(value = "/isPassWord", produces = "text/html;charset=UTF-8")
		@ResponseBody
		public String isPassWord(String passWord,Double balance,String name,String bankId) {
			ResponseMsg msg = new ResponseMsg();
			String json = new String();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				String token = webContext.getRequest().getHeader("Authorization");
				if (token != null) {
					Long userId = consumerUserService.getUserIdByToken(token);
					if (userId == null) {
						msg.setHearder(3, "认证信息错误，请重新登录！");
					} else {
						String payPassWord = myPurseService.getPayPassWord(userId);
						Map balanceMap = myPurseService.getMyBalance(userId);
						MD5 getMD5 = new MD5();
						if(payPassWord.equals(getMD5.GetMD5Code(passWord))){
							Double userBalance = Double.parseDouble((balanceMap.get("balance"))+"");
							//提交申请
							WithdrawalApply withdrawalApply = new WithdrawalApply();
							withdrawalApply.setId(GenerateSequenceUtil.getUniqueId());
							withdrawalApply.setBalance(balance);
							withdrawalApply.setBankId(bankId);
							withdrawalApply.setName(name);
							withdrawalApply.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							withdrawalApply.setBeginBalance(Double.parseDouble(balanceMap.get("balance")+""));
							withdrawalApply.setState(0);
							withdrawalApply.setUserId(userId);
							myPurseService.saveWithdrawalApply(withdrawalApply);
							//更新余额  减操作
							Map map = new HashMap();
							map.put("balance",userBalance-balance);
							map.put("userId",userId);
							int type = 1;
							myPurseService.updateBalanceUserAccount(map,type);
							msg.setHearder(0,"ok");
						}else{
							msg.setHearder(4,"passWord is error");
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
	 * 我的钱包 - 获取银行卡
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/getBank
	 * 流程：
	 * 查询银行卡列表
	 * 调用表
	 * user_bank
	 */
	@RequestMapping(value = "/getBank", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getBank() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				List bankList = new ArrayList();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					bankList = myPurseService.getBank(userId);
					if(bankList.size()>0&&bankList.get(0)!=null)
					{
						msg.setData(bankList);
					}
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
	 * 我的钱包 - 添加银行卡
	 * 参数 bankName bankCode realName idCard type
	 * 	id userId state
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/saveBank?bankname=交通银行&bankcode=123049586746&bankaccount=中国天山大街交通银行&realname=张全全&idcard=1230598678567238x&type=0
	 * 流程
	 * 插入一条银行卡
	 * 调用表user_bank
	 */
	@RequestMapping(value = "/saveBank", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveBank(UserBank userBank) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					userBank.setId(GenerateSequenceUtil.getUniqueId());
					userBank.setState(0);
					userBank.setUserId(userId);
					myPurseService.saveBank(userBank);
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
	 * 我的钱包 - 判断有没有支付密码
	 * 点击支付密码调用此方法
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/payPassWord
	 * 流程
	 * 1，获取支付密码
	 * 2，判断支付密码是否为空
	 * 调用表
	 * 
	 */
	@RequestMapping(value = "/payPassWord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payPassWord() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					String passWord = myPurseService.getPayPassWord(userId);
					if(passWord != null && passWord.length() >0 && !passWord.equals("null")){
						msg.setHearder(0, "ok");
					}else{
						msg.setHearder(4, "dont hava passWord");
						json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
						System.out.println(json);
						return json;
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
	 * 我的钱包 - 更新支付密码
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/updatePayPassWord?passWord=123456
	 * 流程
	 * 更新用户表的密码
	 */
	@RequestMapping(value = "/updatePayPassWord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updatePayPassWord(String passWord,String realName,String idCard) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					MD5 getMD5 = new MD5();
					Map map = new HashMap();
					map.put("id",userId);
					map.put("passWord", getMD5.GetMD5Code(passWord));
					map.put("realName", realName);
					map.put("idCard",idCard);
					myPurseService.updatePayPassWord(map);
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
	 * 我的钱包 - 重置支付密码
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/resetPayPassWord?oldPassWord=123456&newPassWord=654321
	 * 流程
	 * 重置用户表的密码
	 */
	@RequestMapping(value = "/resetPayPassWord", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updatePayPassWord(String oldPassWord,String newPassWord) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					MD5 getMD5 = new MD5();
					Map map = new HashMap();
					map.put("id",userId);
					map.put("passWord", getMD5.GetMD5Code(newPassWord));
					String passWord = myPurseService.getPayPassWord(userId);
					if(passWord!=null&&passWord!="")
					{
						String md5PassWord = getMD5.GetMD5Code(oldPassWord);
						if(passWord.equals(md5PassWord))
						{
							myPurseService.updatePayPassWord(map);
							msg.setHearder(0, "ok");
						}else
						{
							msg.setHearder(5, "原密码不正确！！");
						}
					}else
					{
						msg.setHearder(4, "用户没有设置支付密码！");
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
	 * 我的钱包 - 获取认证的手机号和真实姓名
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/getConsumerInfo
	 * 通过 userid查询用户的认证手机号和真实姓名
	 * 调用表
	 */
	@RequestMapping(value = "/getConsumerInfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getConsumerInfo() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					ConsumerUser user =consumerUserService.selectByUserId(userId);
					Map map = new HashMap();
					if(user.getMobileNo()!=null&&user.getTrueName()!=null)
					{
						map.put("realName", user.getTrueName());
						map.put("telphone", user.getMobileNo());
						msg.setHearder(0,"ok");
						msg.setData(map);
					}else
					{
						msg.setHearder(4,"手机号或者名称没有认证");
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
	 * 我的钱包 - 身份验证
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/authentication?idCard=123456&verification=123456
	 * 通过 身份证号 和手机验证码验证该用户是否正确
	 * 调用表
	 */
	@RequestMapping(value = "/authentication", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String authentication(String idCard,String verification) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					ConsumerUser user =consumerUserService.selectByUserId(userId);
					if (user.getIdCard()!=null&&verification!=null&&verification!=""&&idCard!=null&&user.getMobileNo()!=null)
					{
						if(user.getIdCard().equals(idCard))
						{
							SysVerification fr = sysVerificationService.getMessage(user.getMobileNo());
							if(verification.equals(fr.getVerification()))
							{
								msg.setHearder(0, "ok");
								Map parameterMap = new HashMap();
								parameterMap.put("idCard", idCard);
								parameterMap.put("realName",user.getTrueName());
								msg.setData(parameterMap);
							}else
							{
								msg.setHearder(4, "手机验证码错误");
							}
							
						}else
						{
							msg.setHearder(6, "身份证验证失败");
						}
					}else
					{
						msg.setHearder(5, "身份证、手机号没有认证或验证码为空");
					}
					
					
					/*Map parameterMap = new HashMap();
					Map map = new HashMap();
					parameterMap.put("idCard", idCard);
					parameterMap.put("cardNumber",cardNumber);
					map = myPurseService.getBankByIdCardAndCardNumber(parameterMap);
					if(map != null){
						msg.setHearder(0, "ok");
					}else{
						msg.setHearder(4, "validation fails");
					}*/
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
	 * 我的钱包 - 积分
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/myIntegral?type=1
	 * type 类型（0余额交易1积分交易2结算交易3,商户给用户充值）
	 * 流程
	 * 1，通过userid获取积分
	 * 2，通过userid获取交易记录明细
	 * 调用表
	 * user_account
	 * user_account_log
	 */
	@RequestMapping(value = "/myIntegral", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myIntegral(Integer type) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				Map integralMap = new HashMap();
				List tradeLogList = new ArrayList();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					map.put("type",type);
					map.put("userId",userId);
					integralMap = myPurseService.getMyIntegral(userId);
					tradeLogList = myPurseService.getTradeLog(map);
					Map returnMap = new HashMap();
					returnMap.put("integralMap",integralMap);
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
	 * 我的钱包 - 积分 - 去兑换 - 商品列表
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/integralShopGoods
	 */
	@RequestMapping(value = "/integralShopGoods", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String integralShopGoods() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					//获取我的积分
					Integer integration = myPurseService.getIntegrationByUserId(userId);
					map.put("integration", integration);
					List goodsList = new ArrayList();
					goodsList = myPurseService.getIntegralGoods();
					if(goodsList.size()>0&&goodsList.get(0)!=null)
					{
						map.put("goodsList", goodsList);
						msg.setData(map);
					}
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
	 * 我的钱包 - 积分 - 商品详情
	 * type(0门票，1积分)
	 * http://localhost/cfdScenic/interFace/MyPurse/integralGoodsDetail?id=1
	 */
	@RequestMapping(value = "/integralGoodsDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String integralGoodsDetail(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				Map integralMap = new HashMap();
				List tradeLogList = new ArrayList();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
						Map goodsDetail = new HashMap();
						goodsDetail = myPurseService.getIntegralGoodsDetaili(id);
						if(goodsDetail != null)
						{
							msg.setData(goodsDetail);
						}
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
	 * 我的钱包 - 积分 - 确认订单(保存订单)
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/saveIntegralOrder?name=积分订单&orderDescribe=积分订单描述&startValid=2016-08-09&endValid=2016-09-08&quantity=1&payWay=4&visitorsId=2&type=1&integraPrice=60&addressId=1&price=10
	 * 参数	说明 price是运费
	 * 传参
	 * name orderDescribe startValid endValid quantity payWay visitorsId type integraPrice				
	 * 默认
	 * id payState orderState price createTime (payTime refundTime) realPrice userId orderCode isComment
	 */
	@RequestMapping(value = "/saveIntegralOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveIntegralOrder(VisitorsOrder visitorsOrder) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Long id = GenerateSequenceUtil.getUniqueId();
					visitorsOrder.setId(id);
					visitorsOrder.setPayState(0);
					visitorsOrder.setOrderState(1);
					visitorsOrder.setCreateTime(sdf.format(new Date()));
					visitorsOrder.setRealPrice(visitorsOrder.getPrice());
					visitorsOrder.setUserId(userId);
					visitorsOrder.setOrderCode(GenerateSequenceUtil.getUniqueId()+"");
					visitorsOrder.setIsComment(0);
					visitorsOrder.setType(1);
					myPurseService.saveIntegralOrder(visitorsOrder);
					msg.setData(id);
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
	 *  我的钱包 - 积分 - 提交订单回显
	 *  http://localhost/cfdScenic/interFace/MyPurse/selectVisitorsOrder?id=1608080455348060
	 *  流程
	 *  1，通过id查询订单
	 *  调用表
	 */
	@RequestMapping(value = "/selectVisitorsOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectVisitorsOrder(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = myPurseService.selectVisitorsOrder(id);
					msg.setData(map);
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
	 *  我的钱包 - 积分 - 支付订单回显
	 *  http://localhost:8080/cfdScenic/interFace/MyPurse/selectVisitorsOrder?id=1608080455348060
	 *  流程
	 *  1，通过id查询订单
	 *  调用表
	 */
	@RequestMapping(value = "/selectVisitorsOrderFinsh", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectVisitorsOrderFinsh(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = myPurseService.selectVisitorsOrderFinsh(id);
					msg.setData(map);
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
	 *  我的钱包 - 积分 - 订单列表
	 *  http://localhost:8080/cfdScenic/interFace/MyPurse/selectMyAllVisitorsOrder
	 */
	@RequestMapping(value = "/selectMyAllVisitorsOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectMyAllVisitorsOrder() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List map = myPurseService.selectMyAllVisitorsOrder(userId);
					if(map.size()>0&&map.get(0)!=null)
					{
						msg.setData(map);
					}
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
	 * 我的钱包 - 积分 - 订单详情
	 * http://localhost:8080/cfdScenic/interFace/MyPurse/selectVisitorsOrderDetail?id=1608080506518250
	 */
	@RequestMapping(value = "/selectVisitorsOrderDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectVisitorsOrderDetail(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
//			String token = webContext.getRequest().getHeader("Authorization");
//			if (token != null) {
//				Long userId = consumerUserService.getUserIdByToken(token);
//				if (userId == null) {
//					msg.setHearder(3, "认证信息错误，请重新登录！");
//				} else {
					Map map = myPurseService.selectVisitorsOrderDetail(id);
					msg.setData(map);
					msg.setHearder(0, "ok");
//				}
//			} else {
//				msg.setHearder(2, "token is null");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
}
