package com.htrj.common.utils;

public class TestNet {
public static void main(String[] args) {
	  //发送 POST 请求
	String p ="projectId=asd1234567890"
			+ "&tenders=qwe1234567890"
			+ "&siBidUser=e824c59b-a566-442d-b379-6175b42add80"
			+ "&siBidAmount=480.0"
			+ "&siBidState=1"
			+ "&siBidTime=2015-11-28 05:46:25&"
			+ "endAmountList=p20151128353<,>420.0<,>2015-11-28 05:41:35#p20151128354<,>480.0<,>2015-11-28 05:46:20#";
	String p1 = "projectId=111111111"
			+ "&tenders=222222222"
			+ "&siBidUser=33333333"
			+ "&siBidAmount=4000"
			+ "&siBidState=1"
			+ "&siBidTime=2015-11-21 12:32:57&" +
    		"endAmountList=2222<,>200.12<,>2015-11-21 12:32:57#33333<,>4000.12<,>2015-11-21 12:32:57#44444<,>4000.12<,>2015-11-21 12:32:57";
	
//    String sr=HttpRemoteProxy.sendPost("http://115.29.79.139:8080/sinopsp/apps/ywnoticeinfo/auctionResult.do",
//    		"projectId=111111111&tenders=222222222&siBidUser=33333333&siBidAmount=4000&siBidState=1&siBidTime=2015-11-21 12:32:57&" +
//    		"endAmountList=2222<,>200.12<,>2015-11-21 12:32:57#33333<,>4000.12<,>2015-11-21 12:32:57#44444<,>4000.12<,>2015-11-21 12:32:57");
	String sr=HttpRemoteProxy.sendPost("http://115.29.79.139:8080/sinopsp/apps/ywnoticeinfo/auctionResult.do", p);
    System.out.println(sr);
}
}
