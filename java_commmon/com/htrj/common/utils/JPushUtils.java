package com.htrj.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;


/**
 * 极光推送
 */
public class JPushUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);
	
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("application.properties");
	
	private static final String JPUSH_APPKEY = appPropertiesLoader.getProperty("jpush.appKey");
	private static final String JPUSH_MASTERSECRET = appPropertiesLoader.getProperty("jpush.masterSecret");
	private static final String JPUSH_USER_APPKEY = appPropertiesLoader.getProperty("jpush.userappKey");
	private static final String JPUSH_USER_MASTERSECRET = appPropertiesLoader.getProperty("jpush.usermasterSecret");
    private static final String JPUSH_ALERT = appPropertiesLoader.getPropertyWithChinese("jpush.alert");
    private static final String JPUSH_TITLE = appPropertiesLoader.getPropertyWithChinese("jpush.title");
    private static final String JPUSH_MESSAGE = appPropertiesLoader.getPropertyWithChinese("jpush.message");
    private static final Boolean JPUSH_APNSPRODUCTION = appPropertiesLoader.getBoolean("jpush.apnsProduction");
	
	/**
	 * 推送通知
	 * 
	 * @param alias 设备别名,即用户Id
	 * @param extras 扩展字段
	 */
	public static PushResult sendPushNotification(List<String> alias, Map<String, String> extras) {
		ClientConfig config = ClientConfig.getInstance();
		config.setConnectionTimeout(10 * 1000);	// 10秒
		config.setReadTimeout(10 * 1000); // 10秒
		
		JPushClient jpushClient = new JPushClient(JPUSH_MASTERSECRET, JPUSH_APPKEY, null, config);
		PushPayload payload = buildPushObject_android_and_ios_notification(alias, extras);
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later.", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it.", e);
		}
		return result;
	}
	/**
	 * 推送通知 - 用户
	 * 
	 * @param alias 设备别名,即用户Id
	 * @param extras 扩展字段
	 */
	public static PushResult sendPushNotificationForUser(List<String> alias, Map<String, String> extras) {
		ClientConfig config = ClientConfig.getInstance();
		config.setConnectionTimeout(10 * 1000);	// 10秒
		config.setReadTimeout(10 * 1000); // 10秒
		
		JPushClient jpushClient = new JPushClient(JPUSH_USER_MASTERSECRET, JPUSH_USER_APPKEY, null, config);
		PushPayload payload = buildPushObject_android_and_ios_notification(alias, extras);
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later.", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it.", e);
		}
		return result;
	}
	
	/**
	 * 推送消息
	 * 
	 * @param alias 设备别名,即用户Id
	 * @param extras 扩展字段
	 */
	public static PushResult sendPushMessage(List<String> alias, Map<String, String> extras) {
		ClientConfig config = ClientConfig.getInstance();
		config.setConnectionTimeout(10 * 1000);	// 10秒
		config.setReadTimeout(10 * 1000); // 10秒
		
		JPushClient jpushClient = new JPushClient(JPUSH_MASTERSECRET, JPUSH_APPKEY, null, config);
		PushPayload payload = buildPushObject_android_and_ios_message(alias, extras);
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			//LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later.", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it.", e);
		}
		return result;
	}
	/**
	 * 推送消息 - 用户
	 * 
	 * @param alias 设备别名,即用户Id
	 * @param extras 扩展字段
	 */
	public static PushResult sendPushMessageForUser(List<String> alias, Map<String, String> extras) {
		ClientConfig config = ClientConfig.getInstance();
		config.setConnectionTimeout(10 * 1000);	// 10秒
		config.setReadTimeout(10 * 1000); // 10秒
		
		JPushClient jpushClient = new JPushClient(JPUSH_USER_MASTERSECRET, JPUSH_USER_APPKEY, null, config);
		PushPayload payload = buildPushObject_android_and_ios_message(alias, extras);
		PushResult result = null;
		try {
			result = jpushClient.sendPush(payload);
			//LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later.", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it.", e);
		}
		return result;
	}
	
	private static PushPayload buildPushObject_android_and_ios_notification(List<String> alias, Map<String, String> extras) {
		return PushPayload.newBuilder()
	            .setPlatform(Platform.android_ios())
	            .setAudience(Audience.alias(alias))
	            .setNotification(Notification.newBuilder()
            		.setAlert(extras.get("content"))
            		.addPlatformNotification(AndroidNotification.newBuilder()
        				.setTitle(JPUSH_TITLE)
        				.addExtras(extras).build())
            		.addPlatformNotification(IosNotification.newBuilder()
        				.setAlert(extras.get("content"))
                        .autoBadge()
                        .setSound("default")
        				.addExtras(extras).build())
            		.build())
	            .setOptions(Options.newBuilder()
                    .setApnsProduction(JPUSH_APNSPRODUCTION)
                    .build())
	            .build();
    }
	
	private static PushPayload buildPushObject_android_and_ios_message(List<String> alias, Map<String, String> extras) {
		return PushPayload.newBuilder()
		        .setPlatform(Platform.android_ios())
		        .setAudience(Audience.alias(alias))
				.setMessage(Message.newBuilder()
					.setTitle(JPUSH_TITLE)
		            .setMsgContent(JPUSH_MESSAGE)
		            .addExtras(extras)
		            .build())
		        .setOptions(Options.newBuilder()
		            .setApnsProduction(JPUSH_APNSPRODUCTION)
		            .build())		
		        .build();
    }
	
	public static void main(String[] args) {

//		String userId = String.valueOf("1607121041436750");
//		List<String> alias = Arrays.asList(userId);
		List<String> aaa = new ArrayList<String>();
//		aaa.add("1610110633582710");
//		aaa.add("1610181202541552");
		aaa.add("1610240951480310");
		Map<String, String> extras = new HashMap<String, String>();
//		extras.put("WINNG_TIMES", "812120001");
//		extras.put("WINNG_GOODS_NAME", "666666");
//		extras.put("WINNG_GOODS_TIMES_ID", "2");
		extras.put("content","您有新的消息，请注意查收！");
		extras.put("goodsType","3");
		extras.put("orderCode","0");
		extras.put("siId","0");
		extras.put("type","0");
		//商户
//		sendPushNotification(aaa,extras);
		//用户
		sendPushNotificationForUser(aaa,extras);
		System.out.println(JPUSH_USER_APPKEY);
		System.out.println("推送成功！");
	}

}
