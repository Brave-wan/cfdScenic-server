package com.htrj.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarHelper {
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMdd HH:mm:ss";
	public static final String HHMM = "HH:mm:ss";
	
	
	
	/**
	 * 
	 * @Title: date2Str 
	 * @Description:将时间类型转换为字符串
	 * @param date 传入的时间
	 * @param format 返回字符串格式
	 * @return
	 */
	public static String date2Str(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 
	 * @Title: str2Date 
	 * @Description:将字符串改为时间
	 * @param dateFmt 传入的字符串
	 * @param format 对应的格式
	 * @return
	 */
	public static Date str2Date(String dateStr,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Title: str2Str
	 * @Description: TODO(转换日期的显示格式)
	 * @param date	需要转换的日期
	 * @param formatBefore	转换之前日期的格式
	 * @param formatAfter	转换之后日期的格式
	 * @return
	 */
	public static String str2Str(String date,String formatBefore,String formatAfter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatBefore);
		try {
			Date tmpDate = sdf.parse(date);
			sdf = new SimpleDateFormat(formatAfter);
			return sdf.format(tmpDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @Title: getNow 
	 * @Description:获取当前时间
	 * @return
	 */
	public static String getNow(){
		return date2Str(new Date(), YYYY_MM_DD);
	}
	
	/**
	 * 
	 * @Title: getNowTime 
	 * @Description: TODO(获取当前时间的分秒) 
	 * @param: @return    
	 * @return: String    
	 * @throws
	 */
	public static String getNowTime(){
		return date2Str(new Date(), HHMM);
	}
	
	
	/**
	 * 
	 * @Title: judgeTime 
	 * @Description: TODO(判断当前时间是否在一个时间段之内) 
	 * @param: @param beingTime
	 * @param: @param endTime
	 * @param: @return    
	 * @return: boolean    
	 * @throws
	 */
	public static boolean judgeTime(String beingTime, String endTime){
		Date now = str2Date(getNowTime(), HHMM);
		Date begin =  str2Date(beingTime, HHMM);
		Date end =  str2Date(endTime, HHMM);
		if(now.getTime() >= begin.getTime() && now.getTime()<=end.getTime()){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean judgeBeingTime(String beingTime){
		Date now = str2Date(getNowTime(), HHMM);
		Date begin =  str2Date(beingTime, HHMM);
		
		if(now.getTime() < begin.getTime()){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public static boolean judgeEndTime(String endTime){
		Date now = str2Date(getNowTime(), HHMM);
		Date end =  str2Date(endTime, HHMM);
		
		if(now.getTime() > end.getTime()){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
	/**
	 * 
	 * @Title: addNow 
	 * @Description:获取今天新增天数之后的日期
	 * @param addDay 新增的天数
	 * @return
	 */
	public static Date addNow(Integer addDay){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		return calendar.getTime();
	}
	
	/**
	 * 获取1900年1月1日新增天数后的日期
	 * @param addDay
	 * @return
	 */
	public static Date add1900(Integer addDay){
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900,0,1);
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		return calendar.getTime();
	}
	
	/**
	 * 按照所给格式进行日期转换
	 * 
	 * @param format
	 * @return
	 */
	public static String getDate(String format) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	/**
	 * 
	 * @Title: getCurrTime 
	 * @Description:获取时分秒信息
	 * @return
	 */
	public static String getCurrTime() {
		return getDate("HHmmss");
	}
	
	/**
	 * 
	 * @Title: addMonth 
	 * @Description:获取时间增加月份后的日期
	 * @param oldDate 
	 * @param addMonth
	 * @return
	 */
	public static Date addMonth(Date oldDate,Integer addMonth){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.MONTH, addMonth);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @Title: getLastNow 
	 * @Description:获取今天的截止时间 即:yyyy-MM-dd 23:59:59
	 * @return
	 */
	public static Date getLastNow(){
		String nowStr = getNow();
		String newNowStr = nowStr + " 23:59:59";
		return str2Date(newNowStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Title: minusDate 
	 * @Description:比较两个日期的时间间隔天数  开始日期 - 截止日期
	 * @param startDate 开始日期
	 * @param endDate 截止日期
	 * @return
	 */
	public static Integer minusDate(Date startDate,Date endDate){
		int result = 0;
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(startDate);
		endCal.setTime(endDate);
		while(startCal.after(endCal)){
			endCal.add(Calendar.DATE, 1);
			result++;
		}
		return result==0?0:result-1;
	}

	/**
	 * 获得日期与今年相差的年份数，如年龄
	 */
	public static int getYears(Date date) {
		return getYears(date, new Date());
	}

	/**
	 * 获得两个日期之间相差的年数
	 */
	public static int getYears(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		
		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		
		return year2 - year1;
	}

	/**
	 * 格式化日期
	 */
	public static String format(Date date, String dateFormat) {
		if (date == null)
			return null;

		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(date);
	}
	/**
	* @Title: getMonday
	* @Description: TODO(获取本周周一)
	* @author 张伟烁
	* @param @return    参数
	* @return String    返回类型
	* @throws
	*/ 
	public static String getMonday(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		return sdf.format(c.getTime());
	}
	public static String getWeek(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 7);
		return sdf.format(c.getTime());
	}
}
