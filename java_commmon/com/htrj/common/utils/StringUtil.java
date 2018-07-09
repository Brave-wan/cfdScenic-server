package com.htrj.common.utils;

import java.util.Random;

public class StringUtil {
	/**
	 * 产生一个随机字符串
	 * 
	 * @return
	 */
	public static String randomString(int length) {
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}
	
	public static boolean isEmpt(String str){
		if(str != null && !"".equals(str) && !"null".equals(str) && !"NULL".equals(str))
			return false;
		else
			return true;
	}
	
	
	public static String getValue(String lastValue){
		String tempStr="abcdefghijklmnopqrstuvwxyz0123456789";
		Integer args[]=new Integer[lastValue.length()];
		for(int i=0;i<lastValue.length();i++){
			args[i]=tempStr.indexOf(lastValue.substring(i, i+1));
		}
		int len = tempStr.length()-1;		
		for(int i=lastValue.length()-1;i>=0;i--){
			if(i==lastValue.length()-1)
				args[i]++;
			if(args[i]>len){
				args[i]=0;
				args[i-1]++;
			}
		}		
		if(args.length>lastValue.length()){
			return "溢出";
		}		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lastValue.length();i++){
			sb.append(tempStr.substring(args[i], args[i]+1));
		}
		return sb.toString();
	}
	public static boolean existStrArr(String str,String []strArr){
		for(int i=0;i<strArr.length;i++){
			if(strArr[i].equals(str)){
				return true;
			}
		}
		return false;
	}
}
