package com.htrj.base.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.util.ObjectUtils;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.htrj.base.configuration.app.ApplicationConfigServer;
import com.htrj.base.configuration.webmvc.ApplicationWebConfig;

/**
 * servlet3.0 启动配置类
 * 备注：普通应用可继承AbstractAnnotationConfigDispatcherServletInitializer
 * @Filename: ApplicationInitializer.java
 * @Description: 
 * @Version: 1.0
 * @Author: zckj-watersky
 * @Email:18831118858@163.com
 * @History:<br>
 *<li>Author: watersky</li>
 *<li>Date: 2015年1月19日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class ApplicationInitializer implements WebApplicationInitializer {
	
	public void onStartup(ServletContext container) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		
		Class<?>[] configClasses = getRootConfigClasses();
		if (!ObjectUtils.isEmpty(configClasses)) {
			rootContext.register(configClasses);
		} 
		container.addListener(new ContextLoaderListener(rootContext));
		
		
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.setServletContext(container);
        dispatcherContext.setParent(rootContext);
        
        Class<?>[] webConfigClasses = getServletConfigClasses();
		if (!ObjectUtils.isEmpty(webConfigClasses)) {
			dispatcherContext.register(webConfigClasses);
		}
        
        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        dispatcher.setAsyncSupported(true);
        
        container.addListener(new RequestContextListener());
        
       
        
	} 
	
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {ApplicationConfigServer.class};
	}

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ApplicationWebConfig.class};
	}

}
