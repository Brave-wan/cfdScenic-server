package com.htkj.cfdScenic.app.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.SmsSendRecord;
import com.htkj.cfdScenic.app.model.SysVerification;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.service.AdvertisementService;
import com.htkj.cfdScenic.app.service.MyPurseService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopGoodsService;
import com.htkj.cfdScenic.app.service.SmsSendService;
import com.htkj.cfdScenic.app.service.SysVerificationService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/consumerUser")
public class ConsumerUserController extends BaseController{
	
	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private VisitorsService visitorsService;
	@Autowired
	private ShopGoodsService shopGoodsService;
	@Autowired
	AdvertisementService advertisementService;
	@Autowired
	SysVerificationService sysVerificationService;
	@Autowired
	private SmsSendService smsSendService;
	@Autowired
	private MyPurseService myPurseService;
	
	/*
	 * 登入
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/login
	 * 返回状态信息
	 * 0登入成功
	 * 1登入失败
	 * 4用户名错误
	 * 5密码错误
	 * 返回参数：
	 	token
        userId
	 */
	@RequestMapping(value = "login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(ConsumerUser consumerUser) {
		ResponseMsg msg = new ResponseMsg();
		String mobileNo = consumerUser.getMobileNo();
		ConsumerUser userMessage = consumerUserService.selectByPhone(mobileNo);
		if (userMessage == null) {
			msg.setHearder(4, "用户名错误");
		} else {
			MD5 getMD5 = new MD5();
			String md5userPassword = getMD5.GetMD5Code(consumerUser.getPassword());
			String password = userMessage.getPassword();
			if (password.equalsIgnoreCase(md5userPassword)) {
				Map<String, String> map = new HashMap<String, String>();
				String uid = UUID.randomUUID().toString();
				map.put("token", uid);
				map.put("userId",userMessage.getId().toString());
				userMessage.setToken(uid);
				try{
					consumerUserService.updateUUID(userMessage);
					msg.setHearder(0, "登录成功");
					msg.setData(map);
					webContext.setSessionUser(userMessage);
				}catch(Exception e){
					msg.setHearder(1, "登入失败");
					return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
				}
			} else {
				msg.setHearder(5, "密码错误");
			}

		}
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	
	
	/*
	 * 注册
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/register
	 * 把用户注册的信息存进数据库表里面
	 * 请求参数
	 * 0注册成功
	 * 1注册失败
	 * 4信息不能为空
	 * 5账号以经注册
	 * 6请发送验证码
	 * 7验证码输入错误，请重新输入
	 */
	@RequestMapping(value = "register", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String register(ConsumerUser user, String checkcode) {
		ResponseMsg msg = new ResponseMsg();
		if (user.getMobileNo() != null & user.getPassword() != null & checkcode != null) // 判断是否为空
		{
			ConsumerUser consumerUser = consumerUserService.selectByPhone(user.getMobileNo()); // 判断是否存在
			if (consumerUser == null) {
				SysVerification fr = sysVerificationService.getMessage(user.getMobileNo());
				if (fr != null) {
					if (checkcode.equals(fr.getVerification())) {
						try {
							Long temId=GenerateSequenceUtil.getUniqueId();
							user.setId(temId);
							user.setPassword(MD5.GetMD5Code(user.getPassword()));
							user.setToken(UUID.randomUUID().toString());
							consumerUserService.insertMessage(user);
							UserAccount userAccount=new UserAccount();
							userAccount.setBalance(0D);
							userAccount.setId(GenerateSequenceUtil.getUniqueId());
							userAccount.setIntegration(0);
							userAccount.setUserId(temId);
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
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	
	/*
	 * 获取验证码
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/checkCode
	 * 请求参数
	 * phone		//电话号码
	 * 返回状态
	 * 4信息不能为空
	 */
	@RequestMapping(value = "checkCode", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkCode(SysVerification sysVerification) throws UnsupportedEncodingException {
		ResponseMsg msg = new ResponseMsg();
		try
		{
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
					SysVerification message = sysVerificationService.getMessage(phone);
					if(message==null){
						sysVerification.setId(GenerateSequenceUtil.getUniqueId());
						sysVerification.setVerification(str.toString());
						sysVerification.setCreateTime(new Date().toString());
						sysVerification.setPhone(phone);
						sysVerificationService.insertMessage(sysVerification);
					}else{
						message.setVerification(str.toString());
						sysVerificationService.updateMessage(message);
					}
					msg.setHearder(0, map.get("data"));
				} else {
					msg.setHearder(Integer.parseInt(map.get("state")), map.get("data"));
				}
			} else {
				msg.setHearder(4, "信息不能为空");
			}
		}catch(Exception e)
		{
			msg.setHearder(1, "失败");
		}

		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	
	
	/**
	 * 找回密码
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/findPsw
	 * 请求参数:
	 * mobileNo  		手机号
	 * checkcode		验证码 
	 * password			新密码
	 */
	@RequestMapping(value = "findPsw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findPsw(ConsumerUser user, String checkcode) {
		ResponseMsg msg = new ResponseMsg();
		if (user.getMobileNo() != null & user.getPassword() != null & checkcode != null) // 判断是否为空
		{
			String mobileNo = user.getMobileNo();
			ConsumerUser consumerUser = consumerUserService.selectByPhone(mobileNo); // 判断是否存在
			if (consumerUser != null) {
				SysVerification fr = sysVerificationService.getMessage(mobileNo);
				if (fr != null) {
					if (checkcode.equals(fr.getVerification())) {
						try {
							consumerUser.setPassword(MD5.GetMD5Code(user.getPassword()));
							consumerUser.setMobileNo(mobileNo);
							consumerUserService.update(consumerUser);
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
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	
	
	
	/**
	 * 修改密码
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/editPsw
	 * 请求参数:
	 * mobileNo  		手机号
	 * password			密码 
	 * newPsw			新密码
	 */
	@RequestMapping(value = "editPsw", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editPsw(String oldPsw, String newPsw) {
		
		String token = webContext.getRequest().getHeader("Authorization");
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		if (token != null) {
			Long userId = consumerUserService.getUserIdByToken(token);
			if (userId == null) {
				msg.setHearder(3, "认证信息错误，请重新登录！");
			}else
			{
				ConsumerUser user=consumerUserService.selectByUserId(userId);
				String md5PassWord = MD5.GetMD5Code(oldPsw);
				if(user.getPassword().equals(md5PassWord))
					{
						user.setPassword(MD5.GetMD5Code(newPsw));
						consumerUserService.update(user);
					
					}else
					{
						msg.setHearder(4, "原密码不正确！");
					}
				/*ResponseMsg msg = new ResponseMsg();
				ConsumerUser user
				if (user.getMobileNo() != null & user.getPassword() != null & newPsw != null) // 判断是否为空
				{
					String mobileNo = user.getMobileNo();
					ConsumerUser consumerUser = consumerUserService.selectByPhone(mobileNo); // 判断是否存在
					if(consumerUser!=null){
						String password = consumerUser.getPassword();
						String password2 = consumerUser.getPassword();
						if(password.equalsIgnoreCase(password2)){
							try {
								String getMD5Code = MD5.GetMD5Code(newPsw);
								consumerUser.setPassword(getMD5Code);
								consumerUser.setMobileNo(mobileNo);
								consumerUserService.update(consumerUser);
								msg.setHearder(0, "修改成功");
							} catch (Exception e) {
								msg.setHearder(1, "修改失败");
							}
						} else {
							msg.setHearder(6, "密码错误");
						}
					}else{
						msg.setHearder(5, "账号不存在");
					}
				} else {
					msg.setHearder(4, "信息不能为空");
				}*/
				
			}
		}else
		{
			msg.setHearder(2, "token is null");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	//	return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	
	
	
	

	/**
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/loginOther
	 * 第三方帐号登录
	 */
	@RequestMapping(value = "/loginOther", method = RequestMethod.POST)
	@ResponseBody
	public String loginOther(@RequestParam("openId") String openId,
			@RequestParam("nickName") String nickName,
			@RequestParam("gender") Integer gender,
			@RequestParam(value = "headImg", required = false) String headImg,
			@RequestParam(value = "source", required = false) Integer source) {
		Map<String, String> map = new HashMap<String, String>();
		ResponseMsg msg = new ResponseMsg();
		try {
			ConsumerUser user = consumerUserService.findUserByOpenId(openId);
			if (user == null) {
				user = new ConsumerUser();
				Long uniqueId = GenerateSequenceUtil.getUniqueId();
				user.setId(uniqueId);
				user.setOpenId(openId);
				user.setNickName(nickName);
				user.setHeadImg(headImg);
				user.setSource(source);
				String uid = UUID.randomUUID().toString();
				user.setToken(uid);
				map.put("token", uid);
				map.put("userId",uniqueId.toString());
				consumerUserService.insertMessage(user);
				UserAccount userAccount=new UserAccount();
				userAccount.setBalance(0D);
				userAccount.setId(GenerateSequenceUtil.getUniqueId());
				userAccount.setIntegration(0);
				userAccount.setUserId(uniqueId);
				myPurseService.insertUserAccount(userAccount);
			}else{
				String uid = UUID.randomUUID().toString();
				user.setToken(uid);
				user.setOpenId(openId);
				Long id = user.getId();
				map.put("token", uid);
				map.put("userId",id.toString());
				consumerUserService.updateMessage(user);
				UserAccount userAccount = consumerUserService.getUserAccountByUserId(user.getId());
				if(userAccount == null){
					UserAccount ua=new UserAccount();
					ua.setBalance(0D);
					ua.setId(GenerateSequenceUtil.getUniqueId());
					ua.setIntegration(0);
					ua.setUserId(user.getId());
					myPurseService.insertUserAccount(ua);
				}
			}
			msg.setHearder(0, "成功");
			msg.setData(map);
		} catch (Exception ex) {
			ex.printStackTrace();
			msg.setHearder(1, "失败");
		}
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}


	
	
	/*
	 * 个人中心-修改个人资料
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/editDatum
	 * 把需要修改的资料传过来
		nickName	//昵称
		gender		//性别 0 男 1 女
		headImg		//头像
		mobileNo	//手机号
	 * 调用表 consumer_user
	 */
	@RequestMapping(value="/editDatum",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editDatum(ConsumerUser consumerUser){
		String token = webContext.getRequest().getHeader("Authorization");
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		if (token != null) {
			Long userId = consumerUserService.getUserIdByToken(token);
			if (userId == null) {
				msg.setHearder(3, "认证信息错误，请重新登录！");
			}else
			{
				try {
					if(consumerUser.getOpenId() == null || consumerUser.getOpenId().equals("")){
						consumerUser.setId(userId);
						consumerUserService.editDatum(consumerUser);
						msg.setHearder(0, "success成功");
					}else{
						if(consumerUser.getMobileNo() != null && !consumerUser.getMobileNo().equals("")){
							ConsumerUser cu = consumerUserService.selectByPhone(consumerUser.getMobileNo());
							if(cu != null && consumerUser.getMobileNo().equals(cu.getMobileNo())){
								consumerUser.setId(userId);
								consumerUser.setMobileNo(null);
								consumerUserService.editDatum(consumerUser);
								msg.setHearder(6,"请绑定未注册的手机号！");
							}else{
								String passWord = MD5.GetMD5Code("123456");
								consumerUser.setId(userId);
								consumerUser.setPassword(passWord);
								consumerUserService.editDatum(consumerUser);
								msg.setHearder(5, "绑定成功，初始密码为123456");
							}
						}else{
							consumerUser.setId(userId);
							consumerUserService.editDatum(consumerUser);
							msg.setHearder(0, "success成功");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			}
		}else
		{
			msg.setHearder(2, "token is null");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/*
	 * 个人中心-获取个人资料
	 * post
	 * http://localhost:8080/cfdScenic/consumerUser/getDatum
	 * 把需要修改的资料传过来
		根据token查询用户id  根据id查询用户信息
	 * 调用表 consumer_user
	 */
	@RequestMapping(value="/getDatum",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getDatum(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		if(token!=null)
		{
			Long userId = consumerUserService.getUserIdByToken(token);
			if(userId!=null)
			{
				try {
					ConsumerUser consumerUser=consumerUserService.selectByUserIdTwo(userId);
					msg.setHearder(0, "success");
					msg.setData(consumerUser);
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else
			{
				msg.setHearder(3, "异地登陆");
			}
		}else
		{
			msg.setHearder(2, "token为空");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 首页-搜索
	 * get
	 * http://localhost:8080/cfdScenic/consumerUser/selectMessage
	 * 根据用户传过来的name名称查找符合条件的信息
	 * 返回参数
	 * 景区
		id						//景区id
		name					//名称
		visitorsDescribe		//描述
		openDateId				//开放时间
		price					//原价
		newPrice				//折后价
		createTime				//创建时间
		headImg					//主图
	 * 商品
	 	id						//商品id
		goodsName				//商品名称
		goodsDescribe			//描述
		price					//原价
		newPrice				//新价
		describeImg				//商户首页图片
	 * 调用表 visitors   shop_goods
	 */
	@RequestMapping(value="/selectMessage",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String selectMessage(String name){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		//String token = webContext.getRequest().getHeader("Authorization");
		//Long userId = consumerUserService.getUserIdByToken(token);
		Map<String,Object> content = new HashMap<String, Object>();
		List<Map<String,String>> visitors = new ArrayList<Map<String, String>>();
		List<Map<String,String>> shopGoods = new ArrayList<Map<String, String>>();
		try {
			visitors = visitorsService.selectMessage(name);
			shopGoods = shopGoodsService.selectMessage(name);
			if(visitors.size()>0&&visitors.get(0)!=null)
			{
				content.put("visitors", visitors);
			}else
			{
				content.put("visitors", new ArrayList());
			}
			if(shopGoods.size()>0&&shopGoods.get(0)!=null)
			{
				content.put("shopGoods", shopGoods);
			}else
			{
				content.put("shopGoods", new ArrayList());
			}
			
			msg.setData(content);
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/*
	 * 首页-首页三个推荐景区
	 * get
	 * http://localhost:8080/cfdScenic/consumerUser/tagsVisitors
	 * 搜索热门推荐的景区
	 * 返回参数
	 * 景区
		id						//景区id
		name					//名称
		visitorsDescribe		//描述
		openDateId				//开放时间
		price					//原价
		newPrice				//折后价
		createTime				//创建时间
		headImg					//主图
	 * 调用表 visitors
	 */
	@RequestMapping(value="/indexVisitors",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String indexVisitors(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String,String>> visitors = new ArrayList<Map<String, String>>();
		try {
			visitors = visitorsService.indexVisitors();
			if(visitors.size()>0&&visitors.get(0)!=null)
			{
				msg.setData(visitors);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/*
	 * 首页-热门景区
	 * get
	 * http://localhost:8080/cfdScenic/consumerUser/tagsVisitors
	 * 搜索热门推荐的景区
	 * 返回参数
	 * 景区
		id						//景区id
		name					//名称
		visitorsDescribe		//描述
		openDateId				//开放时间
		price					//原价
		newPrice				//折后价
		createTime				//创建时间
		headImg					//主图
	 * 调用表 visitors
	 */
	@RequestMapping(value="/tagsVisitors",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String tagsVisitors(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String,String>> visitors = new ArrayList<Map<String, String>>();
		try {
			visitors = visitorsService.tagsVisitors();
			if(visitors.size()>0&&visitors.get(0)!=null)
			{
				msg.setData(visitors);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/*
	 * 首页-轮播图
	 * GET
	 * http://localhost:8080/cfdScenic/consumerUser/carouselImg
	 * 把表内轮播的图片返回link_id(链接id)img_url(图片url地址)title(名称)advert_describe(描述)
	 * 调用表 advertisement
	 */
	@RequestMapping(value="/carouselImg",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String carouselImg(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = advertisementService.selectPrimarySideImgUrl();
			if(list.size()>0&&list.get(0)!=null)
			{
				msg.setData(list);
			}
			msg.setHearder(0, "success");
			
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	/*
	 * 首页-推荐商品
	 * get
	 * http://localhost:8080/cfdScenic/consumerUser/recommendShop
	 * 把推荐的商品显示出来
	 * 返回参数
	 * 商品
	 	id						//商品id
		goodsName				//商品名称
		goodsDescribe			//描述
		price					//原价
		newPrice				//新价
		describeImg				//商户首页图片
		isHot					//是否热销（0否，1是）
		monthlySales			//月销量
		type					//商品类型（1酒店2特产3饭店）
	 * 调用表  shop_goods
	 */
	@RequestMapping(value="/recommendShop",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String recommendShop(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String,String>> shopGoods = new ArrayList<Map<String, String>>();
		try {
			shopGoods = shopGoodsService.recommendShop();
			if(shopGoods.size()>0&&shopGoods.get(0)!=null)
			{
				msg.setData(shopGoods);
			}
			
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 首页-广告位
	 * GET
	 * http://localhost:8080/cfdScenic/consumerUser/adPositionId
	 * 把表内轮播的图片返回link_id(链接id)img_url(图片url地址)title(名称)advert_describe(描述)
	 * 调用表 advertisement
	 */
	@RequestMapping(value="/adPositionId",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String adPositionId(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = advertisementService.adPositionId();
			msg.setHearder(0, "success");
			if(list.size()>0&&list.get(0)!=null)
			{
				msg.setData(list);
			}

		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
}
