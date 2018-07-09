package com.htrj.common.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.htrj.common.utils.ConvertRegisterHelper;
import com.htrj.common.utils.DatePropertyEditor;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public abstract class BaseController {
	public final Logger	logger	= LoggerFactory.getLogger(getClass());

	public static final String	SessionKey_Token		= "requestToken";
	public static final String	SessionKey_User			= "SessionKeyUser";
	public static final String	SessionKey_Admin		= "SessionKeyAdmin";
	public static final String	SessionKey_SysControl	= "SessionKeySysControl";
	
	public Cache globalConfigs = null;
	
	public BaseController(){
		globalConfigs = CacheManager.getInstance().getCache("globalConfigs"); 
	}
	
	static {
		// 注册converters
		ConvertRegisterHelper.registerConverters();
	}

	/**
	 * 初始化binder的回调函数.
	 * 
	 * @see MultiActionController#createBinder(HttpServletRequest,Object)
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
		binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}

	protected WebContext	webContext;

	/**
	 * 增加了@ModelAttribute的方法可以在本controller方法调用前执行,可以存放一些共享变量,如枚举值,或是一些初始化操作
	 */
	@ModelAttribute
	protected void init(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// model.put("now", new java.sql.Timestamp(System.currentTimeMillis()));
		// model.put("HtmlTitle", "大医精诚健康管理系统");
		webContext = new WebContext(request, response);
	}

	/**
	 * 创建Token
	 */
	protected String createToken() {
		String token = UUID.randomUUID().toString();
		webContext.setSessionAttr(SessionKey_Token, token);
		return token;
	}

	/**
	 * 移除Token
	 */
	protected void removeToken() {
		webContext.removeSessionAttr(SessionKey_Token);
	}

	/**
	 * 检测Token是否有效
	 */
	protected boolean isTokenValid(String token) {
		return isTokenValid(token, false);
	}

	/**
	 * 检测Token是否有效的同时是否移除Token
	 */
	protected boolean isTokenValid(String token, boolean removeToken) {
		Object origin = webContext.getSessionAttr(SessionKey_Token);
		if (removeToken)
			removeToken();
		return origin == null ? false : origin.equals(token);
	}

}
