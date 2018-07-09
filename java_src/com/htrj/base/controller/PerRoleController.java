package com.htrj.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htrj.base.model.PerRole;
import com.htrj.base.service.PerRoleService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.Json;

@Controller
@RequestMapping("/background/perrole")
public class PerRoleController extends BaseController{

	@Autowired
	private PerRoleService service;
	
	@RequestMapping("insert")
	@ResponseBody
	public Json insert(PerRole perRole){
		Json json = new Json();
		if(perRole.getId()!=null){
			try {
				service.update(perRole);
				json.setSuccess(true);
			} catch (Exception e) {
				json.setSuccess(false);
				e.printStackTrace();
			}
		}else{
			try {
				service.insert(perRole);
				json.setSuccess(true);
			} catch (Exception e) {
				json.setSuccess(false);
				e.printStackTrace();
			}
		}
		return json;
	}
	@RequestMapping("del")
	@ResponseBody
	public Json del(int id){
		Json json = new Json();
		try {
			int num = service.delPerRoleById(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
}
