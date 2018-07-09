package com.htrj.common.utils;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateSequenceUtil {
	/** .log */
	private static final Logger			logger			= LoggerFactory.getLogger(GenerateSequenceUtil.class);
	 
	private static boolean		tmpIDlocked	= false;
	 private static long tmpID = 0;
	/**
	 * 时间格式生成序列
	 * 
	 * @return String
	 */
	 
	 public synchronized static Long getUniqueId() {
	  long ltime = 0;
	  while (true) {
	   if (tmpIDlocked == false) {
	    tmpIDlocked = true;
	    ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS")
	      .format(new Date()).toString()) * 10;
	    if (tmpID < ltime) {
	     tmpID = ltime;
	    } else {
	     tmpID = tmpID + 1;
	     ltime = tmpID;
	    }
	    tmpIDlocked = false;
	    return ltime;
	   }
	  }
	 }


}