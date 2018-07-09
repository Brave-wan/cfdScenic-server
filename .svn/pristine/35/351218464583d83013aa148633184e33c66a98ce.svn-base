package com.htkj.cfdScenic.app.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static void main(String[] args) {

	}
	
	/**
	 * http post 请求
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String httpPost(String url, String body) throws ClientProtocolException, IOException {
		String responseBody = "";

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		StringEntity myEntity = new StringEntity(body, ContentType.create("text/plain", "UTF-8"));
		post.setEntity(myEntity);
		
		logger.debug("Executing request " + post.getRequestLine()+"  \n  body----->>"+body);
		CloseableHttpResponse response = httpclient.execute(post);
		logger.debug("Executed response " +response.getStatusLine().toString());
		
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				responseBody = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			logger.error("http post response Entity to String 失败",e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				logger.error("http post response 链接关闭失败",e);
			}
		}
		return responseBody;
	}

	public static String httpGet(String url) {
		String body = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			HttpGet httpget = new HttpGet(url);

			logger.debug("Executing request " + httpget.getRequestLine());

			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				logger.debug(response.getStatusLine().toString());

				// Get hold of the response entity
				HttpEntity entity = response.getEntity();

				// If the response does not enclose an entity, there is no need
				// to bother about connection release
				if (entity != null) {
					body = EntityUtils.toString(entity, "utf-8");
					logger.debug(body);
				}

			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			logger.error("http get response Entity to String 失败",e);
		} catch (IOException e) {
			logger.error("http get response Entity to String 失败",e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("http post response 链接关闭失败",e);
			}

		}

		return body;

	}
	
}
