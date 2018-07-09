package com.htkj.cfdScenic.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htrj.common.utils.PropertiesLoader;

/**
 * 微信的配置参数
 * @author iYjrg_xiebin
 * @date 2015年11月25日下午4:19:57
 */
@SuppressWarnings("unused")
public class WeixinConfigUtils {

	private static final Log log = LogFactory.getLog(WeixinConfigUtils.class);
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("sys.properties");


	public static String appid;
	public static String mch_id;
	public static String notify_url;

	static {
		/*ResourceBundle bundle = ResourceBundle.getBundle("resources/sys");
		appid = bundle.getString("appid");
		mch_id = bundle.getString("mch_id");
		notify_url = bundle.getString("notify_url");*/

		try{
			InputStream is = WeixinConfigUtils.class.getResourceAsStream("sys.properties");
			Properties properties = new Properties();
			properties.load(is);
			

			
			appid = appPropertiesLoader.getProperty("appid");
			mch_id = appPropertiesLoader.getProperty("mch_id");
			notify_url = appPropertiesLoader.getProperty("notify_url");
		}catch(Exception ex){
			log.debug("加载配置文件"+ex.getMessage());
		}
	}


	public static void main(String[] args) throws IOException {
		InputStream is = WeixinConfigUtils.class.getResourceAsStream("/resources/sys.properties");

		Properties properties = new Properties();

		properties.load(is);

		is.close();
		String appid = properties.getProperty("appid");
		System.out.println(appid);
	}

}
