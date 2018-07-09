package com.htrj.base.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.ShopUser;
import com.htrj.base.model.BaseMenu;
import com.htrj.base.model.SysUser;
import com.htrj.base.service.BaseMenuService;
import com.htrj.base.service.SysUserService;
import com.htrj.common.base.BaseController;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataTree;
import com.htrj.common.resources.CommonStrings;

@Controller
@RequestMapping()
public class LoginController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private BaseMenuService baseMenuService;
	
	//登入
	@RequestMapping("/login")
	public String login(ModelMap model, String name, String password,String yz,HttpSession session) {
		String view;
    	SysUser user = null;
    	if (webContext.isAuthorized()) {
    		user = (SysUser) webContext.getSessionUser();
    		view = CommonStrings.VIEW_INDEX;
    	} else if (name == null || password == null || yz==null||"".equals(yz)||name.trim().equals("") || password.trim().equals("")) {
    		view = CommonStrings.VIEW_LOGIN;
    	} else {
    		try {
    			if (!(yz.equalsIgnoreCase(session.getAttribute("code").toString()))) {  //忽略验证码大小写  
    		    	view = CommonStrings.VIEW_LOGIN;
    				model.put("showMsg", "验证码输入有误！");    
    		    }else{
    		    	user = sysUserService.login(name, password);
    		    	if (user != null) {
    		    		webContext.setSessionUser(user);
    		    		view = CommonStrings.VIEW_INDEX;              
    		    	} else {
    		    		view = CommonStrings.VIEW_LOGIN;          
    		    		model.put("showMsg", "用户不存在！");         
    		    	}
    		    }
    		} catch (BusinessException e) {
    			model.put("showMsg", e.getMessage());
    			view = CommonStrings.VIEW_LOGIN;
    		}
    	}
    	if (view.equals(CommonStrings.VIEW_INDEX)) {
    		model.put("user", user);
    	}
		return view;
	}
	
	/**
	 * 
	 * @Title: getMenuTree 
	 * @Description: TODO(根据父ID取当前客户的菜单，异步加载的。) 
	 * @param: @param id 父ID
	 * @param: @return    
	 * @return: List<DataTree>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/findMenu")
	
	//点击菜单后，进行菜单2级分类
	
	//admin 查看全部
	
	public List<DataTree> getMenuTree(Long id){
		BaseMenu baseMenu = new BaseMenu();
		SysUser sysUser = (SysUser) webContext.getSessionUser();
		baseMenu.setBrId(sysUser.getBRID());
		baseMenu.setBmParentId(id);
		List<DataTree> dataTreeList = baseMenuService.getMenuTree(baseMenu);
		return dataTreeList;
	}

	//shopUser 查看自己的
	@ResponseBody
	@RequestMapping("/getMenuShopTree")
	public List<DataTree> getMenuShopTree(Long id){
		BaseMenu baseMenu = new BaseMenu();
		ShopUser sysUser = (ShopUser) webContext.getSessionShopUser();
		baseMenu.setBrId(sysUser.getBRID());
		baseMenu.setBmParentId(id);
		List<DataTree> dataTreeList = baseMenuService.getMenuTree(baseMenu);
		return dataTreeList;
	}
	
	
	/**
	 * 
	 * @Title: logout 
	 * @Description: TODO(登出方法) 
	 * @param: @return    
	 * @return: String    
	 * @throws
	 */
	@RequestMapping("/logout")
	public String logout() {
				SysUser sysUser = (SysUser) webContext.getSessionUser();
				webContext.setSessionUser(null);
				return CommonStrings.VIEW_LOGIN;
	}
/**
 * 
 * @Title: shoplogout 
 * @Description: TODO(商家登出方法) 
 * @param: @return    
 * @return: String    
 * @throws
 */
@RequestMapping("/shoplogout")
public String shoplogout() {
		ShopUser user = (ShopUser) webContext.getSessionShopUser();
		webContext.setSessionUser(null);
		return CommonStrings.VIEW_PC_LOGIN;
}
	
	//测试
	@RequestMapping("/ceshi")
	public String ceshi(){
		return "/home/image";
	}
	
	
	
}
