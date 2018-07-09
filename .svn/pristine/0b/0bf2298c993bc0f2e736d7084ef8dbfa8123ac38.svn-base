package com.htrj.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htrj.base.model.BaseRole;
import com.htrj.base.service.BaseRoleService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("/baseRole")
public class BaseRoleController extends BaseController {

	@Autowired
	private BaseRoleService baseRoleService;
	
	/**
	 * 
	 * @Title: getBaseRolePage 
	 * @Description: TODO(跟据参数查询角色列表) 
	 * @param: @param baseRole 查询参数
	 * @param: @param pagerForm 分页属性
	 * @param: @return    
	 * @return: DataGrid    
	 * @throws
	 */
	@RequestMapping("/getBaseRolePage")
	public DataGrid getBaseRolePage(BaseRole baseRole, PagerForm pagerForm){
		DataGrid data = baseRoleService.pageBaseRoleByParam(pagerForm.getPageRequest(baseRole.toMap()));
		return data;
	}
	
	@RequestMapping("/toBaseRoleList")
	public String toBaseRoleList(){
		return "/base/baseRoleList";
	}
}
