package com.htrj.common.utils.wxPay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.htkj.cfdScenic.app.util.MD5Util;
import com.htrj.common.utils.JPushUtils;
import com.htrj.common.utils.PropertiesLoader;

public class WXPay {
	//参数存在配置文件
	protected static final Logger LOG = LoggerFactory.getLogger(JPushUtils.class);
	
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("application.properties");
	
	private static final String WX_APPID = appPropertiesLoader.getProperty("wx.appid");
	private static final String WX_MCH_ID = appPropertiesLoader.getProperty("wx.mch_id");
	private static final String WX_NOTIFY_URL = appPropertiesLoader.getProperty("wx.notify_url");
	private static final String WX_TRADE_TYPE = appPropertiesLoader.getProperty("wx.trade_type");
	private static final String WX_KEY = appPropertiesLoader.getProperty("wx.key");

	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static Map<String,String> getPrePayId(String body,Integer balance,String orderCode,HttpServletRequest re,HttpServletResponse rp){
		//参数组
		String appid = WX_APPID;
		//商户的ID
		String mch_id = WX_MCH_ID;
		//随机字符串（为了不让订单重复）
		String nonce_str = getRandomString(16);
		//订单号
		String out_trade_no = orderCode;
		
		//留着
		String detail = "备用参数，先留着，后面会有用的";
		String attach = "备用参数，先留着，后面会有用的";
		
		//订单金额，单位（分）
		int total_fee = balance;//单位是分
		//app实际地址IP
		String spbill_create_ip = re.getRemoteAddr();
		System.out.println(spbill_create_ip);
		//订单开始期
		String time_start = timeStart();
		System.out.println(time_start);
		//订单结束期
		String time_expire = timeExpire();
		//回调地址，app端调用接口不需要回调地址
		String notify_url = WX_NOTIFY_URL;
		//请求类型
		String trade_type = WX_TRADE_TYPE;
		
		
		//参数：开始生成签名
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("appid", appid);
		parameters.put("mch_id", mch_id);
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", body);
		parameters.put("detail", detail);
		parameters.put("attach", attach);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("total_fee", total_fee);
		parameters.put("time_start", time_start);
		parameters.put("time_expire", time_expire);
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", trade_type);
		parameters.put("spbill_create_ip", spbill_create_ip);
		
		String sign = createSign("UTF-8", parameters);

		Unifiedorder unifiedorder = new Unifiedorder();
		unifiedorder.setAppid(appid);
		unifiedorder.setMch_id(mch_id);
		unifiedorder.setNonce_str(nonce_str);
		unifiedorder.setSign(sign);
		unifiedorder.setBody(body);
		unifiedorder.setDetail(detail);
		unifiedorder.setAttach(attach);
		unifiedorder.setOut_trade_no(out_trade_no);
		unifiedorder.setTotal_fee(total_fee);
		unifiedorder.setSpbill_create_ip(spbill_create_ip);
		unifiedorder.setTime_start(time_start);
		unifiedorder.setTime_expire(time_expire);
		unifiedorder.setNotify_url(notify_url);
		unifiedorder.setTrade_type(trade_type);

		
		//构造xml参数
		String xmlInfo = xmlInfo(unifiedorder);
		
		String wxUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		String method = "POST";
		
		String weixinPost = httpsRequest(wxUrl, method, xmlInfo).toString();
		
		Map<String,String> map = jdomParseXml(weixinPost);
		return map;
	}
	
	

	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";   
		Random random = new Random();   
		StringBuffer sb = new StringBuffer();
		int number = 0;
		for (int i = 0; i < length; i++) {   
			number = random.nextInt(base.length());   
			sb.append(base.charAt(number));   
		}   
		return sb.toString();   
	}   
	
	/*
	 * 订单开始交易的时间
	 */
	public static String timeStart(){
		return df.format(new Date());
	}
	
	/*
	 * 订单开始交易的时间
	 */
	public static String timeExpire(){
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,30);
		return df.format(now.getTimeInMillis());
	}
	
	
		//http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=4_3
		//商户Key：改成公司申请的即可
		//32位密码设置地址：http://www.sexauth.com/  jdex1hvufnm1sdcb0e81t36k0d0f15nc
		private static String Key = WX_KEY;

		/**
		 * 微信支付签名算法sign
		 * @param characterEncoding
		 * @param parameters
		 * @return
		 */
		@SuppressWarnings("rawtypes")
		public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){
			StringBuffer sb = new StringBuffer();
			Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
			Iterator it = es.iterator();
			while(it.hasNext()) {
				Map.Entry entry = (Map.Entry)it.next();
				String k = (String)entry.getKey();
				Object v = entry.getValue();
				if(null != v && !"".equals(v) 
						&& !"sign".equals(k) && !"key".equals(k)) {
					sb.append(k + "=" + v + "&");
				}
			}
			sb.append("key=" + Key);
			System.out.println("字符串拼接后是"+sb.toString());
			String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
			return sign;
		}
		
		/**
		 * 开始post提交参数到接口
		 * 并接受返回
		 * @param url
		 * @param xml
		 * @param method
		 * @param contentType
		 * @return
		 */
		public static String xmlHttpProxy(String url,String xml,String method,String contentType){
			InputStream is = null;
			OutputStreamWriter os = null;

			try {
				URL _url = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) _url.openConnection();
				conn.setDoInput(true);   
				conn.setDoOutput(true);   
				conn.setRequestProperty("Content-type", "text/xml");
				conn.setRequestProperty("Pragma:", "no-cache");  
				conn.setRequestProperty("Cache-Control", "no-cache");  
				conn.setRequestMethod("POST");
				os = new OutputStreamWriter(conn.getOutputStream());
				os.write(new String(xml.getBytes(contentType)));
				os.flush();

				//返回值
				is = conn.getInputStream();
				return getContent(is, "utf-8");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					if(os!=null){os.close();}
					if(is!=null){is.close();}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		/**
		 * 解析返回的值
		 * @param is
		 * @param charset
		 * @return
		 */
		public static String getContent(InputStream is, String charset) {
			String pageString = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			StringBuffer sb = null;
			try {
				isr = new InputStreamReader(is, charset);
				br = new BufferedReader(isr);
				sb = new StringBuffer();
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				pageString = sb.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (is != null){
						is.close();
					}
					if(isr!=null){
						isr.close();
					}
					if(br!=null){
						br.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				sb = null;
			}
			return pageString;
		}

		/**
		 * 构造xml参数
		 * @param xml
		 * @return
		 */
		public static String xmlInfo(Unifiedorder unifiedorder){
			//构造xml参数的时候，至少又是个必传参数
			/*
			 * <xml>
				   <appid>wx2421b1c4370ec43b</appid>
				   <attach>支付测试</attach>
				   <body>JSAPI支付测试</body>
				   <mch_id>10000100</mch_id>
				   <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>
				   <notify_url>http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php</notify_url>
				   <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>
				   <out_trade_no>1415659990</out_trade_no>
				   <spbill_create_ip>14.23.150.211</spbill_create_ip>
				   <total_fee>1</total_fee>
				   <trade_type>JSAPI</trade_type>
				   <sign>0CB01533B8C1EF103065174F50BCA001</sign>
				</xml>
			 */

			if(unifiedorder!=null){
				StringBuffer bf = new StringBuffer();
				bf.append("<xml>");

				bf.append("<appid><![CDATA[");
				bf.append(unifiedorder.getAppid());
				bf.append("]]></appid>");

				bf.append("<mch_id><![CDATA[");
				bf.append(unifiedorder.getMch_id());
				bf.append("]]></mch_id>");

				bf.append("<nonce_str><![CDATA[");
				bf.append(unifiedorder.getNonce_str());
				bf.append("]]></nonce_str>");

				bf.append("<sign><![CDATA[");
				bf.append(unifiedorder.getSign());
				bf.append("]]></sign>");

				bf.append("<body><![CDATA[");
				bf.append(unifiedorder.getBody());
				bf.append("]]></body>");

				bf.append("<detail><![CDATA[");
				bf.append(unifiedorder.getDetail());
				bf.append("]]></detail>");

				bf.append("<attach><![CDATA[");
				bf.append(unifiedorder.getAttach());
				bf.append("]]></attach>");

				bf.append("<out_trade_no><![CDATA[");
				bf.append(unifiedorder.getOut_trade_no());
				bf.append("]]></out_trade_no>");

				bf.append("<total_fee><![CDATA[");
				bf.append(unifiedorder.getTotal_fee());
				bf.append("]]></total_fee>");

				bf.append("<spbill_create_ip><![CDATA[");
				bf.append(unifiedorder.getSpbill_create_ip());
				bf.append("]]></spbill_create_ip>");

				bf.append("<time_start><![CDATA[");
				bf.append(unifiedorder.getTime_start());
				bf.append("]]></time_start>");

				bf.append("<time_expire><![CDATA[");
				bf.append(unifiedorder.getTime_expire());
				bf.append("]]></time_expire>");

				bf.append("<notify_url><![CDATA[");
				bf.append(unifiedorder.getNotify_url());
				bf.append("]]></notify_url>");

				bf.append("<trade_type><![CDATA[");
				bf.append(unifiedorder.getTrade_type());
				bf.append("]]></trade_type>");


				bf.append("</xml>");
				return bf.toString();
			}

			return "";
		}


		
		
		/**
		 * post请求并得到返回结果
		 * @param requestUrl
		 * @param requestMethod
		 * @param output
		 * @return
		 */
		public static String httpsRequest(String requestUrl, String requestMethod, String output) {
			try{
				URL url = new URL(requestUrl);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod(requestMethod);
				if (null != output) {
					OutputStream outputStream = connection.getOutputStream();
					outputStream.write(output.getBytes("UTF-8"));
					outputStream.close();
				}
				// 从输入流读取返回内容
				InputStream inputStream = connection.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String str = null;
				StringBuffer buffer = new StringBuffer();
				while ((str = bufferedReader.readLine()) != null) {
					buffer.append(str);
				}
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				inputStream = null;
				connection.disconnect();
				return buffer.toString();
			}catch(Exception ex){
				ex.printStackTrace();
			}

			return "";
		}
		
		/**
		 * 1、DOM解析
		 */
		@SuppressWarnings("rawtypes")
		public static void beginXMLParse(String xml){
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(xml); // 将字符串转为XML

				Element rootElt = doc.getRootElement(); // 获取根节点smsReport

				System.out.println("根节点是："+rootElt.getName());

				Iterator iters = rootElt.elementIterator("sendResp"); // 获取根节点下的子节点sms

				while (iters.hasNext()) {
					Element recordEle1 = (Element) iters.next();
					Iterator iter = recordEle1.elementIterator("sms");

					while (iter.hasNext()) {
						Element recordEle = (Element) iter.next();
						String phone = recordEle.elementTextTrim("phone"); // 拿到sms节点下的子节点stat值

						String smsID = recordEle.elementTextTrim("smsID"); // 拿到sms节点下的子节点stat值

						System.out.println(phone+":"+smsID);
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 2、DOM4j解析XML（支持xpath）
		 * 解析的时候自动去掉CDMA
		 * @param xml
		 */
		public static void xpathParseXml(String xml){
			try { 
				StringReader read = new StringReader(xml);
				SAXReader saxReader = new SAXReader();
				Document doc = saxReader.read(read);
				String xpath ="/xml/appid";
				System.out.print(doc.selectSingleNode(xpath).getText());  
			} catch (DocumentException e) {
				e.printStackTrace();
			}  
		}

		/**
		 * 3、JDOM解析XML
		 * 解析的时候自动去掉CDMA
		 * @param xml
		 */
		@SuppressWarnings("unchecked")
		public static Map<String,String> jdomParseXml(String xml){
			Map<String,String> map = new HashMap<String,String>();
			try { 
				StringReader read = new StringReader(xml);
				// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
				InputSource source = new InputSource(read);
				// 创建一个新的SAXBuilder
				SAXBuilder sb = new SAXBuilder();
				// 通过输入源构造一个Document
				org.jdom.Document doc;
				doc = (org.jdom.Document) sb.build(source);

				org.jdom.Element root = doc.getRootElement();// 指向根节点
				List<org.jdom.Element> list = root.getChildren();

				if(list!=null&&list.size()>0){
					for (org.jdom.Element element : list) {
						System.out.println("key是："+element.getName()+"，值是："+element.getText());
						try{
							map.put(element.getName(), element.getText());
//							methodName =  element.getName();
//							Method m = v.getClass().getMethod("set" + methodName, new Class[] { String.class });
//							if(parseInt(methodName)){
//								m.invoke(v, new Object[] { Integer.parseInt(element.getText()) });
//							}else{
//								m.invoke(v, new Object[] { element.getText() });
//							}
						}catch(Exception ex){
							ex.printStackTrace();
						}

					}
				}
				return map;
			} catch (JDOMException e) {
				e.printStackTrace();
			}  catch (IOException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}

		public static boolean parseInt(String key){
			if(!StringUtils.isEmpty(key)){
				if(key.equals("total_fee")||key.equals("cash_fee")||key.equals("coupon_fee")||key.equals("coupon_count")||key.equals("coupon_fee_0")){
					return true;
				}
			}
			return false;
		}


}
