package com.htrj.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htrj.base.model.SysUser;
import com.htrj.common.base.BaseController;
import com.htrj.common.resources.CommonStrings;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

	/**
	 * 直接访问http://url:port/项目名/时的处理
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping()
	public String index(HttpServletRequest request, ModelMap model) {
		logger.debug(request.getSession().getServletContext().getRealPath("/"));

		if (webContext.isAuthorized()) {
			SysUser user = (SysUser) webContext.getSessionUser();
			model.put("user", user);
			return CommonStrings.VIEW_INDEX;
		}
		return CommonStrings.VIEW_LOGIN;
	}
	
	/**
	 * 
	 * @Title: welcome 
	 * @Description: TODO(欢迎页面) 
	 * @param: @param model
	 * @param: @return    
	 * @return: String    
	 * @throws
	 */
	@RequestMapping("welcome")
	public String welcome(ModelMap model) {
		return CommonStrings.VIEW_WELCOME;
	}
		

}
