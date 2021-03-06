package com.htrj.base.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htrj.base.model.BaseMenu;
import  com.htrj.base.model.Permission;
import com.htrj.base.model.Role;
import com.htrj.base.service.BaseMenuService;
import com.htrj.base.service.PermissionService;
import com.htrj.base.service.RoleService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.ResponseUtil;
/**
 * 权限设置
 * 张腾跃
 * 2016年9月29日 11:03:19
 */

@Controller
@RequestMapping("/background/permiss")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private BaseMenuService baseMenuService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/toPermission")
	public String Permission(){
		return "/background/permission/Manager";
	}
	@RequestMapping("/getPermissionList")
	@ResponseBody
	public DataGrid getpermissionList(PagerForm pagerForm , Permission permission){
		return permissionService.topermissionManage(pagerForm.getPageRequest(permission.toMap()));
	}
	@RequestMapping("/findmenu")
	public String findmenu(Model model,Map<String,Object> map,String name,int id){
		List<BaseMenu> list = permissionService.findUserMenu(map);
		try {
			name = new String(name.getBytes("iso8859-1" ),"UTF-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			map.put("brid", id);
			List<Role> role = roleService.findAll(map);
			if(role.size()>0){
				Role role1 = role.get(0);
				model.addAttribute("role", role1.getMenuid());
				model.addAttribute("roleid", role1.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list", list);
		model.addAttribute("name", name);
		return "/background/permission/toEditPage";
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Json insert(Role role,String menuid,int brid,Map map){
		Json json = new Json();
		map.put("brid", brid);
		List list = roleService.findAll(map);
		if(list.size()>0){
			role=(Role) list.get(0);
			role.setBrid(brid);
			if(menuid!=null){
				role.setMenuid(menuid);
			}else{
				role.setMenuid("");
			}
			try {
				roleService.update(role);
				json.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
			}
		}else{
			if(menuid!=null){
				role.setMenuid(menuid);
			}else{
				role.setMenuid("");
			}
			try {
				roleService.insert(role);
				json.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
			}
		}
		return json;
	}
	/**
	 * 分级菜单
	 */
	@RequestMapping("authMenuAction")
	private void authMenuAction(HttpServletRequest request,int type,HttpServletResponse response,Map map)
			throws ServletException, IOException {
		String parentId="-1";
		String roleId=request.getParameter("roleId");
		try{
			map.put("brid", roleId);
			String authIds=permissionService.getAuthIdsById(map);
			JSONArray jsonArray=permissionService.getCheckedAuthsByParentId(parentId,authIds,type);
			ResponseUtil.write(response, jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
