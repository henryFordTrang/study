package com.study.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParseUtil {

	/**
	 * @param datdString Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
	 * @return 年月日;
	 */
	public static String parseTime(String datdString) {
		
		//datdString Thu May 18 2017 00:00:00 GMT 0800 (中国标准时间)
		datdString = datdString.replace("GMT 0800", "+0800").replaceAll("\\(.*\\)", "");
		//datdString Thu May 18 2017 00:00:00 GMT+0800 (中国标准时间)
	    datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
	    //将字符串转化为date类型，格式2016-10-12
	    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
	    Date dateTrans = null;
	    try {
	        dateTrans = format.parse(datdString);
//	        return new SimpleDateFormat("yyyy-MM-dd").format(dateTrans).replace("-","/");
	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTrans);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return datdString;

	}

	/**
	 * @param datdString "Tue Jul 12 12:10:11 GMT+08:00 2016";
	 * @return 时分秒
	 */
	public static String parseHour(String datdString) {

	    datdString = datdString.replace("GMT", "").replaceAll("\\(.*\\)", "");
	    SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
	    Date dateTrans = null;
	    try {
	        dateTrans = format.parse(datdString);
	        return new SimpleDateFormat("HH:mm:ss").format(dateTrans);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return datdString;
	}

	public static String parseDate(String datdString) {

		SimpleDateFormat sdf1= new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

	    SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    try {
	      datdString = sdf2.format(sdf1.parse(datdString));
	    } catch (ParseException e) {
	      e.printStackTrace();
	    } 
	    return datdString;
	}
}
