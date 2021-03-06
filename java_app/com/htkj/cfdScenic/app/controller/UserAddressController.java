package com.htkj.cfdScenic.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.UserAddress;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.UserAddressService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/userAddress")
public class UserAddressController extends BaseController{
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private UserAddressService userAddressService;
	
	
	/*
	 * 个人设置-添加收货地址
	 * post
	 * http://localhost:8080/cfdScenic/userAddress/receiptAddress
	 * 用户新增添加收货地址    
	 * 请求参数
		userName		//姓名
		telphone		//电话
		placeAddress	//所在地址
		detailAddress	//详细地址
		postcode		//邮编
	 * 调用表 user_address
	 */
	@RequestMapping(value="/receiptAddress",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String receiptAddress(UserAddress userAddress){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		//测试数据
		//token = "";
		//userId = 1l;
		if(token!=null){
			if(userId!=null){
				try {
					userAddress.setId(GenerateSequenceUtil.getUniqueId());
					userAddress.setUserId(userId);
					userAddress.setState(0);
					//是否以有收货地址(0没有)
					Integer number = userAddressService.selectByUser(userId);
					if(number == 0){
						userAddress.setIsDefault(1);
					}else{
						userAddress.setIsDefault(0);
					}
					userAddressService.insertMessage(userAddress);
					msg.setHearder(0, "success");
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 个人设置-编辑收货地址
	 * post
	 * http://localhost:8080/cfdScenic/userAddress/editAddress
	 * 用户根据用户地址ID编辑自己的收货地址    
	 * 请求参数
	 	id				//用户地址ID
		userName		//姓名
		telphone		//电话
		placeAddress	//所在地址
		detailAddress	//详细地址
		postcode		//邮编
	 * 调用表 user_address
	 */
	@RequestMapping(value="/editAddress",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editAddress(UserAddress userAddress){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			//查询当前地址有没有订单，有的话不能修改地址
			Long id = userAddress.getId();
			Integer addressCount = userAddressService.getOrderCountByAddressId(id);
			if(addressCount != 0){
				UserAddress newUserAddress = userAddressService.selectById(id);
				newUserAddress.setState(1);
				userAddressService.updateMessage(userAddress);
				
				UserAddress ua = new UserAddress();
				ua.setId(GenerateSequenceUtil.getUniqueId());
				ua.setUserName(userAddress.getUserName());
				ua.setTelphone(userAddress.getTelphone());
				ua.setPlaceAddress(userAddress.getPlaceAddress());
				ua.setDetailAddress(userAddress.getDetailAddress());
				ua.setPostcode(userAddress.getPostcode());
				ua.setState(0);
				userAddressService.insertMessage(ua);
				msg.setHearder(0, "success");
			}else{
				UserAddress newUserAddress = userAddressService.selectById(id);
				newUserAddress.setUserName(userAddress.getUserName());
				newUserAddress.setTelphone(userAddress.getTelphone());
				newUserAddress.setPlaceAddress(userAddress.getPlaceAddress());
				newUserAddress.setDetailAddress(userAddress.getDetailAddress());
				newUserAddress.setPostcode(userAddress.getPostcode());
				userAddressService.updateMessage(userAddress);
				msg.setHearder(0, "success");
			}
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/*
	 * 个人设置-删除收货地址
	 * post
	 * http://localhost:8080/cfdScenic/userAddress/deleteAddress 
	 * 用户根据用户地址ID删除用户自己的收货地址
	 * 调用表 user_address
	 */
	@RequestMapping(value="/deleteAddress",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteAddress(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Integer addressCount = userAddressService.getOrderCountByAddressId(id);
			if(addressCount != 0){
				userAddressService.updateAddressById(id);
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
	 * 个人设置-设为默认地址
	 * post
	 * http://localhost:8080/cfdScenic/userAddress/defaultAddress
	 * 用户根据用户地址ID编辑修改设置成默认的收货地址
	 * 调用表 user_address
	 */
	@RequestMapping(value="/defaultAddress",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String defaultAddress(UserAddress userAddress){
 		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		//测试数据
//		token = "";
//		userId = 1l;
		if(token!=null){
			if(userId!=null){
				try {
					//把全部地址置成非默认
					userAddressService.editAllDefault(userId);
					Long id = userAddress.getId();
					userAddressService.updateDefault(id);
					msg.setHearder(0, "success");
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	
	
	
	/*
	 * 个人设置-收货地址列表
	 * get
	 * http://localhost:8080/cfdScenic/userAddress/addressList
	 * 用户根据用户地址ID查看自己的收货地址列表
	 * 返回参数
	 	id				//地址id
	 	userId			//用户id
	 	userName		//姓名
		telphone		//电话
		placeAddress	//所在地址
		detailAddress	//详细地址
		postcode		//邮编
		isDefault		//是否默认（0是1否）
	 * 调用表 user_address
	 */
	@RequestMapping(value="/addressList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addressList(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		//测试数据
		//token="";
		//userId=1l;
		if(token!=null){
			if(userId!=null){
				try {
					list = userAddressService.addressList(userId);
					msg.setHearder(0, "success");
					if(list.size()>0&&list.get(0)!=null)
					{
						msg.setData(list);
					}
				} catch (Exception e) {
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
}
