package com.htkj.cfdScenic.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.HtmlText;
import com.htkj.cfdScenic.app.model.UserOpinion;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.service.UserOpinionService;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/userOpinion")
public class UserOpinionController extends BaseController{
	@Autowired
	private HtmlTextService visitorsProfilesService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private ShopUserService shopUserService;
	@Autowired
	private UserOpinionService userOpinionService;
	
	/*
	 * 个人设置-意见反馈
	 * post
	 * http://localhost:8080/cfdScenic/userOpinion/opinion
	 * 把用户的意见反馈信息存进数据库表里
		decribe		//描述
		email		//邮箱
		memo		//备注
	 * 调用表 user_opinion
	 */
	@RequestMapping(value="/opinion",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String opinion(UserOpinion userOpinion){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String token = webContext.getRequest().getHeader("Authorization");
		//测试数据
		//token = "";
		if(token!=null){
			Long userId = consumerUserService.getUserIdByToken(token);
			//测试数据
			//userId = 1l;
			if(userId!=null){
				try {
					Integer ShopNumber = shopUserService.selectById(userId);
					Integer userNumber = consumerUserService.selectById(userId);
					if(ShopNumber!=0){
						userOpinion.setShopId(userId);
					}else if(userNumber!=0){
						userOpinion.setUserId(userId);
					}
					userOpinion.setId(GenerateSequenceUtil.getUniqueId());
					userOpinionService.insertMessage(userOpinion);
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
	@RequestMapping("/totousu")
	public String totousu(){
		return "/background/userOpinion/Manager";
	}
	@RequestMapping("list")
	@ResponseBody
	public DataGrid list(PagerForm pagerForm,UserOpinion userOpinion){
		DataGrid dataGrid = new DataGrid();
		return userOpinionService.getAll(pagerForm.getPageRequest(userOpinion.toMap()));
	}
	@RequestMapping("yl")
	public String yl(long id,ModelMap model){
		try {
			Map map = userOpinionService.selectById(id);
			model.put("model", map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/background/userOpinion/yl";
	}
}
