package com.htrj.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件多人发送，可设置发送，抄送，密送
 * 
 * @author zhutongyu
 * 
 */
public class SendMail {

	private static SendMail instance = null;

	private SendMail() {

	}

	public static SendMail getInstance() {
		if (instance == null) {
			instance = new SendMail();
		}
		return instance;
	}

	public void send(String to[], String cs[], String ms[], String subject, String content, String formEmail,
			String fileList[]) {
		try {
			Properties p = new Properties(); // Properties p =
			// System.getProperties();
			p.put("mail.smtp.auth", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.exmail.qq.com");
			p.put("mail.smtp.port", "25");
			// 建立会话
			Session session = Session.getInstance(p);
			Message msg = new MimeMessage(session); // 建立信息
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			msg.setFrom(new InternetAddress(formEmail)); // 发件人

			String toList = null;
			String toListcs = null;
			String toListms = null;

			// 发送,
			if (to != null) {
				toList = getMailList(to);
				InternetAddress[] iaToList = new InternetAddress().parse(toList);
				msg.setRecipients(Message.RecipientType.TO, iaToList); // 收件人
			}

			// 抄送
			if (cs != null) {
				toListcs = getMailList(cs);
				InternetAddress[] iaToListcs = new InternetAddress().parse(toListcs);
				msg.setRecipients(Message.RecipientType.CC, iaToListcs); // 抄送人
			}

			// 密送
			if (ms != null) {
				toListms = getMailList(ms);
				InternetAddress[] iaToListms = new InternetAddress().parse(toListms);
				msg.setRecipients(Message.RecipientType.BCC, iaToListms); // 密送人
			}
			msg.setSentDate(new Date()); // 发送日期
			msg.setSubject(subject); // 主题
			msg.setText(content); // 内容
			// 显示以html格式的文本内容
			messageBodyPart.setContent(content, "text/html;charset=gbk");
			multipart.addBodyPart(messageBodyPart);

			// 2.保存多个附件
			if (fileList != null) {
				addTach(fileList, multipart);
			}

			msg.setContent(multipart);
			// 邮件服务器进行验证
			Transport tran = session.getTransport("smtp");
			tran.connect("smtp.exmail.qq.com", "system@hvlx.net","Hvlx123");
			tran.sendMessage(msg, msg.getAllRecipients()); // 发送
			System.out.println("邮件发送成功");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 添加多个附件
	public void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(), "GBK", "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}

	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}

	public static void main(String args[]) throws MessagingException {
		SendMail send = SendMail.getInstance();
		String to[] = {"765949827@qq.com"};
		String cs[] = {"285970744@qq.com"};
		String ms[] = null;
		String subject = "入保信息";
		String content = "姓名：李某某<br>身份证号：130124199599999999";
		String formEmail = "system@hvlx.net";
		
		String[] arrArchievList = null;
		
		
//		String[] arrArchievList = new String[4];
//		arrArchievList[0] = "c:\\2012052914033429140297.rar";
//		arrArchievList[1] = "c:\\topSearch.html";
//		arrArchievList[2] = "c:\\topSearch2.html";
//		arrArchievList[3] = "c:\\logo_white.png";
		// 2.保存多个附件
		send.send(to, cs, ms, subject, content, formEmail, arrArchievList);
		System.out.println("发送成功!");
		
		
		
//		Properties p = new Properties(); // Properties p =
//		// System.getProperties();
//		p.put("mail.smtp.auth", "true");
//		p.put("mail.transport.protocol", "smtp");
//		p.put("mail.smtp.host", "smtp.exmail.qq.com");
//		p.put("mail.smtp.port", "465");
//		// 建立会话
//		Session session = Session.getInstance(p);
//		
//		Transport tran;
//		try {
//			tran = session.getTransport("smtp");
//			tran.connect("smtp.exmail.qq.com", "system@hvlx.net","Hvlx123");
//			
//			System.err.println("连接成功！");
//		} catch (NoSuchProviderException e) {
//			System.err.println("连接失败！");
//			e.printStackTrace();
//		}
// 帐号system@hvlx.net
//密码：hvlx123.
//发送：13601109081@163.com
//密送：522179011@qq.com
	}

}