package com.htrj.common.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.htrj.common.base.BaseController;
import com.htrj.common.base.WebContext;

 

public class CheckLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		try {
			String lookupPath = ph.getLookupPathForRequest(request);
			for (String tourl : passurls) {
				if (pm.match(tourl, lookupPath)) {
					return true;
				}
			}
			log.debug("------lookupPath-------"+lookupPath);
			return webBeforeHandle(request, response, handler);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("preHandle ERROR!");
		}
		return true;
	}

	public boolean webBeforeHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		WebContext ctx = new WebContext(request, response);

		boolean isAuthorized = ctx.isAuthorized()
				|| ctx.isSetSessionAttr(BaseController.SessionKey_Admin);
		
		// 已经登录
		if (isAuthorized) {
			return true;
		}
		/**
		 * 张腾跃
		 * 2016年10月9日11:23:03
		 * 双重判断session
		 */
		else{
			isAuthorized = ctx.isAuthorized()
					|| ctx.isSetSessionAttr(BaseController.SessionKey_User);
			if (isAuthorized) {
				return true;
			}
		}
		log.debug("---------- 登录检测失败，跳转至登录页面 ----------");
		ctx.sendRedirect("/logout");
		return false;
	}

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ArrayList<String> passurls = new ArrayList<String>();

	private UrlPathHelper ph = new UrlPathHelper();
	private PathMatcher pm = new AntPathMatcher();

	public ArrayList<String> getPassurls() {
		return passurls;
	}

	public void setPassurls(ArrayList<String> passurls) {
		this.passurls = passurls;
	}

}
