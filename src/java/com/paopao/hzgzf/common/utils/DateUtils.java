/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static int getDistanceDayOfTwoDate(Date before, Date after) {
//		long beforeTime = before.getTime();
//		long afterTime = after.getTime();
//		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
		if (before.compareTo(after) >= 0) {
			return 0;
		}
		int months = getDistanceMonthOfTwoDate(before, after);
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(before);
		calendar2.setTime(after);
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
		int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
		int days = day2 - day1;
		if (days < 0) {
			Date tempDate = addMonths(before, months);
			calendar1.setTime(tempDate);
			int maxDay = calendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
			days = day2 + (maxDay - day1);
		}
		return days;
	}
	/**
	 * 获取两个日期之间的月数
	 * @param before
	 * @param after
	 * @return
	 */
	public static int getDistanceMonthOfTwoDate(Date before, Date after){
		if (before.compareTo(after) >= 0) {
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(before);
		calendar2.setTime(after);
		int distance = (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR))*12 + calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
		if (calendar2.get(Calendar.DAY_OF_MONTH) < calendar1.get(Calendar.DAY_OF_MONTH)) {
			distance--;
		}
		return distance;
	}
	
	/**
	 * 获取日期所在月份的天数
	 * @param date
	 * @return
	 */
	public static int getMaxday(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//		System.out.println(formatDateTime(getDefaultExpireDate()));
		
//		Date before = parseDate("2015/5/6");
//		Date end = parseDate("2015/6/5");
//		System.out.println(getDistanceMonthOfTwoDate(before, end));
//		System.out.println(getDistanceDayOfTwoDate(before, end));
//		Date temp = addMonths(before, 10);
//		System.out.println(formatDate(temp, "yyyy-MM-dd"));
	}
	
	/**
	   * 在一个时间上加上对应的年
	   * @param ti long
	   * @param i int
	   * @throws Exception
	   * @return Date
	   */
	  public static Date addOrMinusYear(long ti, int i) throws Exception{
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.YEAR, i);
	    rtn = cal.getTime();
	    return rtn;
	  }

	  /**
	   * 在一个时间上加上对应的月份数
	   * @param ti long
	   * @param i int
	   * @throws Exception
	   * @return Date
	   */
	  public static Date addOrMinusMonth(long ti, int i){
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.MONTH, i);
	    rtn = cal.getTime();
	    return rtn;
	  }
	  /**
	   * 在一个时间上加上或减去周
	   * @param ti long
	   * @param i int
	   * @return Date
	   */
	  public static Date addOrMinusWeek(long ti, int i) {
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.WEEK_OF_YEAR, i);
	    rtn = cal.getTime();
	    return rtn;
	  }

	  /**
	   * 在一个时间上加上或减去天数
	   * @param ti long
	   * @param i int
	   * @return Date
	   */
	  public static Date addOrMinusDays(long ti, int i) {
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.DAY_OF_MONTH, i);
	    rtn = cal.getTime();
	    return rtn;
	  }
	  /**
	   * 在一个时间上加上或减去小时
	   * @param ti long
	   * @param i int
	   * @return Date
	   */
	  public static Date addOrMinusHours(long ti, int i) {
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.HOUR, i);
	    rtn = cal.getTime();
	    return rtn;
	  }
	  /**
	   * 在一个时间上加上或减去分钟
	   * @param ti long
	   * @param i int
	   * @return Date
	   */
	  public static Date addOrMinusMinutes(long ti, int i) {
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.MINUTE, i);
	    rtn = cal.getTime();
	    return rtn;
	  }
	  /**
	   * 在一个时间上加上或减去秒数
	   * @param ti long
	   * @param i int
	   * @return Date
	   */
	  public static Date addOrMinusSecond(long ti, int i) {
	    Date rtn = null;
	    GregorianCalendar cal = new GregorianCalendar();
	    Date date = new Date(ti);
	    cal.setTime(date);
	    cal.add(GregorianCalendar.SECOND, i);
	    rtn = cal.getTime();
	    return rtn;
	  }
	  
	  /**
	     * 根据指定日期获取当天的最后的时间点
	     *
	     * @param date
	     * @return
	     */
	    public static Date getEndDateOfDay(Date date) {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.set(Calendar.HOUR_OF_DAY, 23);
	        rightNow.set(Calendar.MILLISECOND, 59);
	        rightNow.set(Calendar.SECOND, 59);
	        rightNow.set(Calendar.MINUTE, 59);
	        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
	        return rightNow.getTime();
	    }
	    
	    /**
	     * 根据指定日期获取当天的最后的时间点
	     *
	     * @param date
	     * @return
	     */
	    public static Date getBeginDateOfDay(Date date) {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.set(Calendar.HOUR_OF_DAY, 0);
	        rightNow.set(Calendar.MILLISECOND, 0);
	        rightNow.set(Calendar.SECOND, 0);
	        rightNow.set(Calendar.MINUTE, 0);
	        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
	        return rightNow.getTime();
	    }

	    /**
	     * 根据指定日期获取前一天的最后的时间点
	     *
	     * @param date
	     * @return
	     */
	    public static Date getPreLastDateOfDay(Date date) {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.set(Calendar.DAY_OF_MONTH,rightNow.get(Calendar.DAY_OF_MONTH)-1);
	        rightNow.set(Calendar.HOUR_OF_DAY, 23);
	        rightNow.set(Calendar.MILLISECOND, 59);
	        rightNow.set(Calendar.SECOND, 59);
	        rightNow.set(Calendar.MINUTE, 59);
	        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
	        return rightNow.getTime();
	    }
	    
	    /**
	     * 根据指定日期获取当天的最后的时间点
	     *
	     * @param date
	     * @return
	     */
	    public static Date getBeginDateOfMonth(Date date) {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.setTime(date);
	        rightNow.set(Calendar.DAY_OF_MONTH, 1);
	        rightNow.set(Calendar.HOUR_OF_DAY, 0);
	        rightNow.set(Calendar.MILLISECOND, 0);
	        rightNow.set(Calendar.SECOND, 0);
	        rightNow.set(Calendar.MINUTE, 0);
	        rightNow.set(Calendar.MONTH, rightNow.get(Calendar.MONTH));
	        return rightNow.getTime();
	    }
	    /**
	     * 获取当月的第一天
	     * @return
	     */
	    public static int getFirstDayOfMonth(){
	    	Calendar rightNow = Calendar.getInstance();
	    	return rightNow.getActualMinimum(Calendar.DAY_OF_MONTH);
	    }
	    /**
	     * 获取当月的最后一天
	     * @return
	     */
	    public static int getLastDayOfMonth(){
	    	Calendar rightNow = Calendar.getInstance();
	    	return rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
	    }
	    
	    /**
	     * 是否是当月最后一天
	     * @return
	     */
	    public static boolean isLastDayOfMonth(){
	    	Calendar rightNow = Calendar.getInstance();
	    	int lastDay = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
	    	int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
	    	if (currentDay == lastDay) {
				return true;
			}
	    	return false;
	    }
	    
	    public static Date getDefaultExpireDate() {
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.set(Calendar.YEAR, 2099);
	        rightNow.set(Calendar.MONTH, 11);
	        rightNow.set(Calendar.DAY_OF_MONTH,31);
	        rightNow.set(Calendar.HOUR_OF_DAY, 23);
	        rightNow.set(Calendar.MILLISECOND, 59);
	        rightNow.set(Calendar.MINUTE, 59);
	        rightNow.set(Calendar.SECOND, 59);
	        return rightNow.getTime();
	    }
}
