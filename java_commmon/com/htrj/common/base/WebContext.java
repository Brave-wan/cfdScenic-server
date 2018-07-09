package com.htrj.common.base;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;


/**
 * Web环境的封装类，方便使用request和response
 */
public class WebContext {


	private HttpServletRequest	request;
	private HttpServletResponse	response;

	public WebContext() {
	}

	public WebContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setCharacterEncoding("utf8");
		try {
			this.request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * 获得系统绝对路径 如：F:\webapps\CmsSys
	 * 
	 * @param path
	 *            可以传入空串
	 */
	public String getAppRealPath(String path) {
		return request.getSession().getServletContext().getRealPath(path);
	}

	/**
	 * 获得应用绝对根路径
	 */
	public String getAppRoot() {
		return getAppRealPath("/");
	}

	/**
	 * 获得系统根路径 如：/CmsSys
	 */
	public String getAppCxtPath() {
		return request.getContextPath();
	}

	/**
	 * 获得应用端口号
	 */
	public int getServerPort() {
		return request.getServerPort();
	}

	/**
	 * 注销
	 */
	public void logout() {
		SecurityUtils.getSubject().logout();
			 
	}

	/**
	 * 获取request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 获取response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取session
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * 从Request的Attribute中获取值
	 */
	public Object getRequestAttr(String key) {
		return request.getAttribute(key);
	}

	/**
	 * 从Request的Parameter中获取值
	 */
	public Object getRequestParam(String key) {
		return request.getParameter(key);
	}

	/**
	 * 向Request的Attribute中赋值
	 */
	public void setRequestAttr(String key, Object value) {
		request.setAttribute(key, value);
	}

	/**
	 * 从Session的Attribute中获取值
	 */
	public Object getSessionAttr(String key) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		} else {
			return session.getAttribute(key);
		}
	}

	/**
	 * 向Session的Attribute中赋值
	 */
	public void setSessionAttr(String key, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}

	/**
	 * 判断Session中是否有此值
	 */
	public boolean isSetSessionAttr(String key) {
		return getSessionAttr(key) != null;
	}

	/**
	 * 移除Session中的属性
	 */
	public void removeSessionAttr(String key) {
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}

	/**
	 * 从Application中获得值
	 */
	public Object getApplicationAttr(String key) {
		return request.getSession().getServletContext().getAttribute(key);
	}

	/**
	 * 向Application中赋值
	 */
	public void setApplicationAttr(String key, Object value) {
		request.getSession().getServletContext().setAttribute(key, value);
	}
	
	public void removeApplicationAttr(String key) {
		request.getSession().getServletContext().removeAttribute(key);
	}

	/**
	 * 获得SessionId
	 */
	public String getSessionId(boolean isCreate) {
		HttpSession session = request.getSession(isCreate);
		if (session == null) {
			return null;
		} else {
			return session.getId();
		}
	}

	/**
	 * 获得访问者IP
	 */
	public String getRemoteIp() {
		return request.getRemoteAddr();
	}

	/**
	 * 获得访问者端口号
	 */
	public int getRemotePort() {
		return request.getRemotePort();
	}

	/**
	 * 获得访问者URL
	 */
	public String getRequestURL() {
		return request.getRequestURL().toString();
	}

	/**
	 * 获得访问者浏览器
	 */
	public String getRequestBrowser() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 1) {
			return agents[1].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者操作系统
	 */
	public String getRequestOs() {
		String userAgent = getRequestUserAgent();
		String[] agents = userAgent.split(";");
		if (agents.length > 2) {
			return agents[2].trim();
		} else {
			return null;
		}
	}

	/**
	 * 获得访问者的代理全部信息
	 */
	public String getRequestUserAgent() {
		String userAgent = request.getHeader("user-agent");
		return userAgent;
	}

	/**
	 * 添加cookie
	 */
	public void addCookie(Cookie cookie) {
		response.addCookie(cookie);
	}

	/**
	 * 获取cookie
	 */
	public Cookie getCookie(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(name)) {
					return c;
				}
			}
		}
		return null;
	}

	/**
	 * 是否是post请求
	 */
	public boolean isMethodPost() {
		String method = request.getMethod();
		if ("post".equalsIgnoreCase(method)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 重定向
	 * 
	 * @param path
	 *            重定向的路径，如/Login
	 * @throws IOException
	 */
	public void sendRedirect(String path) throws IOException {
		response.sendRedirect(getAppCxtPath() + path);
	}

	/**
	 * 判断Session是否已经授权
	 */
	public boolean isAuthorized() {
		//用户是否登录
		return getSessionUser()!=null;
//		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	/**
	 * 从Session中取得授权用户
	 */
	public Object getSessionUser() {
		HttpSession session = request.getSession();
		if (session.getAttribute(BaseController.SessionKey_Admin) != null) {
			return  session.getAttribute(BaseController.SessionKey_Admin);
		} else {
			return null;
		}
	}
	/**
	 * 从Session中取得授权商户
	 */
	public Object getSessionShopUser() {
		HttpSession session = request.getSession();
		if (session.getAttribute(BaseController.SessionKey_User) != null) {
			return  session.getAttribute(BaseController.SessionKey_User);
		} else {
			return null;
		}
	}
	/**
	 * 将已授权用户保存到Session中
	 */
	public void setSessionUser(Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(BaseController.SessionKey_Admin, value);
	}
	/**
	 * 将已授权用户保存到Session中
	 */
	public void setSessionShopUser(Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(BaseController.SessionKey_User, value);
	}

	public Object getSysControl() {
		Session session = SecurityUtils.getSubject().getSession();
		if (session.getAttribute(BaseController.SessionKey_SysControl) != null) {
			return  session.getAttribute(BaseController.SessionKey_SysControl);
		} else {
			return null;
		}
	}

	public void setSysControl(Object basoption) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(BaseController.SessionKey_SysControl, basoption);
		
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("SysControl", basoption);
		
	}

	 

}
