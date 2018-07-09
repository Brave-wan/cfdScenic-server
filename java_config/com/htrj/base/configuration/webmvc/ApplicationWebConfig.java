package com.htrj.base.configuration.webmvc;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsHtmlView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

import com.htrj.common.controller.CheckLoginInterceptor;
import com.htrj.common.exception.CustomSimpleMappingExceptionResolver;


@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages  = { "com.**.controller", "com.htrj.**.controller", "com.htrj.common.exception"})
public class ApplicationWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * JSP页面跳转配置
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
		jspViewResolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		jspViewResolver.setPrefix("/WEB-INF/views");
		jspViewResolver.setSuffix(".jsp");
		jspViewResolver.setOrder(1);
		return jspViewResolver;
	}
 
	/**
	 * 忽略静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
		registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
		registry.addResourceHandler("/attached/**").addResourceLocations("/attached/");
		registry.addResourceHandler("/uploads/**").addResourceLocations("/uploads/");
		registry.addResourceHandler("/weboffice/**").addResourceLocations("/weboffice/");

		
	}

	/**
	 * 上传组件
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver re = new CommonsMultipartResolver();
		re.setMaxInMemorySize(10240);
		re.setDefaultEncoding("UTF-8");
		re.setMaxUploadSize(-1);
		return re;
	}

	@Bean(name = "exceptionResolver")
	public CustomSimpleMappingExceptionResolver exceptionResolver() {
		CustomSimpleMappingExceptionResolver exceptionResolver = new CustomSimpleMappingExceptionResolver();
		exceptionResolver.setDefaultErrorView("/commons/error/error");
		Properties mappings = new Properties();
		mappings.setProperty("com.htrj.common.exception.BusinessException", "/commons/error/error");
		exceptionResolver.setExceptionMappings(mappings);
		return exceptionResolver;
	}

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		ArrayList<String> list = new ArrayList<String>();

		list.add("/");
		list.add("/login");
		list.add("/logout");
		list.add("/upload/**");
		list.add("/images/**");
		list.add("/ireport/**");
		list.add("/scripts/**");
		list.add("/styles/**");
		
		list.add("/index");
		list.add("/findMenu");
		list.add("/getMenuShopTree");
		list.add("/file/**");
		list.add("/webSocket/webSocketServer");
		list.add("/webSocket/webSocketServer/**");
		list.add("/webSocket/sockjs/webSocketServer");
		list.add("/webSocket/sockjs/webSocketServer/**");
		list.add("/webservice/**");
		list.add("/base/basecompany/toRegister");
		list.add("/base/basecompany/register");
		list.add("/base/basezoning/treeFindAll");
		list.add("/base/dictionaryDetail/findAll");
		list.add("/base/basecompany/toPrompt");
		list.add("/toPreside");
		list.add("/toBidder");
		list.add("/toPreside/**");
		list.add("/toBidder/**");
		list.add("/a/**");
		list.add("/test1/**");
		list.add("/user/**");
		list.add("/UserService/**");
		
		list.add("/find/**");
		list.add("/account/**");
		list.add("/editPhoto/**");
		list.add("/background/Order/**");
		list.add("/background/other/**");
		list.add("/background/shareRule/**");
		list.add("/background/shopInformation/**");
		list.add("/background/travel/**");
		list.add("/Travelorder/**");
		list.add("/userbank/**");
		list.add("/background/way/**");
		list.add("/img/**");
		
		
		//cfd
		list.add("/way/**");
		list.add("/monitorPoint/**");
		list.add("/visitorsOrder/**");
		list.add("/advertisement/**");
		list.add("/consumerUser/**");
		list.add("/newsNotice/**");
		list.add("/userAccountLog/**");
		list.add("/userAddress/**");
		list.add("/userOpinion/**");
		list.add("/visitors/**");
		list.add("/visitorsOrder/**");
		list.add("/visitorsProfiles/**");
		list.add("/interFace/eShop/**");
		list.add("/hotelOrder/**");
		list.add("/restaurantOrder/**");
		list.add("/shopUser/**");
		list.add("/recharge/**");
		list.add("/interFace/**");
		list.add("/interFace/**/**");
		list.add("/interFace/PlayCircle/**");
		
		list.add("/ShopUserPcController/**");
		list.add("/ShopUserPcController/**/**");
		list.add("/background/shopInformationManager/renzShopInformation");
		list.add("/background/shopInformationManager/toAlipayInfo");
		list.add("/background/shopInformationManager/editAlipayInfo");
		list.add("/background/shopInformationManager/**");
		list.add("/background/publicPlaces/**");
		list.add("/ShopUserPcController/registsmrz");
		list.add("/background/permiss/*");
		list.add("/background/perrole/*");
		list.add("/background/shopGoodsManage/addshopGoods");
		list.add("/background/goodsOrderManage/**");
		list.add("/background/visitorsManage/**");
		list.add("/share/**");
		list.add("/pictureLibrary/uploadPicture");
		list.add("/background/advertisement/**");
		list.add("/background/advertisingPage/**");
		
		
		
		
		list.add("/img/**");
		list.add("/city/**");
		CheckLoginInterceptor checklogin = new CheckLoginInterceptor();
		checklogin.setPassurls(list);
		registry.addInterceptor(checklogin);
		// registry.addInterceptor(new LocaleInterceptor());
		// registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("/").excludePathPatterns("/admin/");
	}
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true);
//		configurer.mediaType("json", new MediaType(MediaType.APPLICATION_JSON_VALUE));
//		configurer.mediaType("xml", new MediaType(MediaType.APPLICATION_XML_VALUE));
	}

}
