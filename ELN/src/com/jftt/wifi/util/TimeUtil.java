/**
 * All rights Reserved, Copyright (C) 江苏富士通通信技术有限公司 2011<BR>
 * 
 * FileName: TimeUtil.java <BR>
 * Version: $Id: TimeUtil.java, v 1.00 2011-03-18 $ <BR>
 * Modify record: <BR>
 * NO. |     Date         |    Name                 |      Content <BR>
 * 1   | 2011-03-18       | JFTT)Dai Wenbin           | original version <BR>
 */
package com.jftt.wifi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TimeUtil {
	// 定义默认的日期格式
	private static final String PATTERN = "yyyy-MM-dd";
	// 指定一天的毫秒数
	private static final Long MILLISECOND_DAY = 86400000L; // 1000L*60*60*24;

	public static Long getMillisecondDay() {
		return MILLISECOND_DAY;
	}

	// 创建指定格式的SimpleDateFormat实例，若不指定格式，则使用默认日期格式
	private static SimpleDateFormat getSimpleDateFormatInstance(String pattern) {
		return new SimpleDateFormat(pattern == null ? PATTERN : pattern);
	}

	// 将Date按指定格式转换为字符串表示
	public static String parseDate2String(Date date, String pattern) {
		return getSimpleDateFormatInstance(pattern).format(date);
	}

	// 默认日期格式
	public static String parseDate2String(Date date) {
		return parseDate2String(date, null);
	}

	// 将字符串形式的日期按指定格式转换为Date
	public static Date parseString2Date(String date, String pattern)
	throws ParseException {
		return getSimpleDateFormatInstance(pattern).parse(date);
	}

	// 默认日期格式
	public static Date parseString2Date(String date) throws ParseException {
		return parseString2Date(date, null);
	}

	// 将Calendar按指定格式转换为String
	public static String parseCalendar2String(Calendar calendar, String pattern) {
		return parseDate2String(calendar.getTime(), pattern);
	}

	// 默认日期格式
	public static String parseCalendar2String(Calendar calendar) {
		return parseCalendar2String(calendar, null);
	}

	// 将String按指定格式转换为Calendar
	public static Calendar parseString2Calendar(String date, String pattern)
	throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseString2Date(date, pattern));
		return calendar;
	}

	// 默认日期格式
	public static Calendar parseString2Calendar(String date)
	throws ParseException {
		return parseString2Calendar(date, null);
	}

	// 将Date转换为Calendar
	public static Calendar parseDate2Calendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	// 将Calendar转换为Date
	public static Date parseCalendar2Date(Calendar calendar) {
		return calendar.getTime();
	}

	// 计算两个日期相隔多少天
	public static int getDaysFrom2Dates(Calendar calendar1, Calendar calendar2) {
		return (int) (Math.abs(calendar1.getTimeInMillis()
				- calendar2.getTimeInMillis()) / MILLISECOND_DAY);
	}

	// 计算两个日期相隔多少天
	public static int getDaysFrom2Dates(Date date1, Date date2) {
		return (int) (Math.abs(date1.getTime() - date2.getTime()) / MILLISECOND_DAY);
	}

	// 计算两个格式相同的String日期相隔多少天,使用指定日期格式
	public static int getDaysFrom2Dates(String date1, String date2,
			String pattern) throws ParseException {
		return getDaysFrom2Dates(parseString2Date(date1, pattern),
				parseString2Date(date2, pattern));
	}

	// 计算两个格式相同的String日期相隔多少天,使用默认日期格式
	public static int getDaysFrom2Dates(String date1, String date2)
	throws ParseException {
		return getDaysFrom2Dates(date1, date2, null);
	}

	// 距离指定日期n天的string日期（n<0表示在指定日期之前）
	public static String getOtherDay(String date, String pattern, Integer n)
	throws ParseException {
		Calendar calendar = getOtherDay(parseString2Calendar(date, pattern), n);
		return parseCalendar2String(calendar, pattern);
	}

	// 默认日期格式
	public static String getOtherDay(String date, Integer n)
	throws ParseException {
		return getOtherDay(date, null, n);
	}
	
	public static String getOtherDayOfToday(Integer n)
			throws ParseException {
				return getOtherDay(TimeUtil.getCurrentTime("yyyy-MM-dd"), null, n);
			}

	// 距离指定日期n天的Date日期（n<0表示在指定日期之前）
	public static Date getOtherDay(Date date, Integer n) {
		return getOtherDay(parseDate2Calendar(date), n).getTime();
	}

	// 距离指定日期n天的Calendar日期（n<0表示在指定日期之前）
	public static Calendar getOtherDay(Calendar calendar, Integer n) {
		calendar.add(Calendar.DAY_OF_MONTH, n);// 此处参数用Calendar中的DAY_OF_MONTH或DAY_OF_YEAR或DAY_OF_WEEK效果等同
		return calendar;
	}

	// 判断是否为周六、周日，为周六、周日返回true，否则返回false
	// 1-7:表示星期日--星期六
	public static boolean isWeekend(Calendar calendar) {
		int i = calendar.get(Calendar.DAY_OF_WEEK);
		if (i == 1 || i == 7) {
			return true;
		}
		return false;
	}

	// 取得某年中某月的天数
	public static int getMonthDays(int year, int month) {
		switch (month) {
		case 1:
			return 31;
		case 2:
			if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
				return 29;
			} else {
				return 28;
			}
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return 0;
		}
	}

	// 获取当前日期是星期几，返回如"星期五"的字符串
	public static String getWeekDay() {
		int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		switch (i) {
		case 1:
			return "\u661f\u671f\u65e5"; // 星期日
		case 2:
			return "\u661f\u671f\u4e00"; // 星期一
		case 3:
			return "\u661f\u671f\u4e8c"; // 星期二
		case 4:
			return "\u661f\u671f\u4e09"; // 星期三
		case 5:
			return "\u661f\u671f\u56db"; // 星期四
		case 6:
			return "\u661f\u671f\u4e94"; // 星期五
		default:
			return "\u661f\u671f\u516d"; // 星期六
		}
	}

	// 获取默认格式的当前系统时间,返回如"1988-03-16"的字符串
	public static String getCurrentTime() {
		return getCurrentTime(null);
	}
	
	public static String getLastDay(){
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_MONTH, -1);
		String year = c.get(c.YEAR)+"";
		int m = c.get(c.MONTH)+1;
		String month = m<10?"0"+m:m+"";
		String day = c.get(c.DAY_OF_MONTH)<10?"0"+c.get(c.DAY_OF_MONTH):c.get(c.DAY_OF_MONTH)+"";
		return year+"-"+month+"-"+day;
	}
	
	

	// 获取指定格式的当前系统时间String
	public static String getCurrentTime(String pattern) {
		return parseDate2String(new Date(), pattern);
	}

	// 获取yyyyMM格式的当前时间的上个月。如当前时间是1988年3月，返回字符串"198802"
	// 如当前时间是1988年1月，返回字符串"198712"
	public static String getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		if (month == 0) {
			year--;
			month = 12;
		}
		return year + ((month + "").length() == 1 ? "0" + month : (month + ""));
	}
	
	// 距离指定日期n月的日期（n<0表示在指定日期之前） yearmonth格式为"201112"表示2011年12月
	public static String getOtherMonth(String yearmonth, int n){
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4, 6));
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, 1);
		c.add(c.MONTH, n);
		String currentMonth = (c.get(c.MONTH)+1)+"";
		if(currentMonth.length()==1){
			currentMonth = "0" + currentMonth;
		}
		return c.get(c.YEAR)+""+currentMonth;
	}
	

	// 获取当前日期所在周是当前年份的第几周，返回length=2的字符串
	public static String getWeekOfYear() {
		String num = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + "";
		return num.length() == 1 ? "0" + num : num;
	}

	//通过日期获得当前周
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(1);
		c.setTime (date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	//通过年份获得当前年有多少周
	public static int getMaxWeekNumOfYear(int year) { 
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		int num = getWeekOfYear(c.getTime());
		if(num == 1) {
			c.set(year, Calendar.DECEMBER, 31-7, 23, 59, 59);
			num = getWeekOfYear(c.getTime());
		}
		return num;
	}

	// 获取当前日期所在周的上一周是当前年份的第几周，返回如"11"的字符串
	// 如果当前周是本年第一周，则返回"01"
	public static String getLastWeekOfYear() {
		int num = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		if (num > 1) {
			num--;
		}
		return (num + "").length() == 1 ? "0" + num : "" + num;
	}

	// 获取当前年份以及当前日期所在周是当前年份的第几周，返回"201101"类型的字符串
	public static String getWeekName() {
		return getCurrentTime("yyyy") + getWeekOfYear();
	}

	// 获取当前年份以及当前日期上周是当前年份的第几周，返回"201101"类型的字符串
	public static String getLastWeekName() {
		return getCurrentTime("yyyy") + getLastWeekOfYear();
	}

	// 获取当前时间 ，精确到秒
	public static String getStringDate() {
		// Date currentTime = new Date();
		// SimpleDateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// String dateString = formatter.format(currentTime);
		// return dateString;
		return getCurrentTime("yyyy-MM-dd HH:mm:ss");
	}

	// 获取yyyyMM格式的当前时间的上个月。如当前时间是1988年3月，返回字符串"198802"
	// 如当前时间是1988年1月，返回字符串"198712"
	public static String getLastMonth(String yearmonth) {
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4));
		if (month == 1) {
			year--;
			month = 12;
		}else {
			month -= 1;
		}
		return year + ((month + "").length() == 1 ? "0" + month : (month + ""));
	}
	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) { 
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(1);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // SUNDAY
		return c.getTime ();
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setMinimalDaysInFirstWeek(1);
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
	
	/**
	 * 取得当前日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getFirstStringDayOfWeek(int year, int week) { 
		Date d = getFirstDayOfWeek(year, week);
		return parseDate2String(d);
	}

	/**
	 * 取得当前日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastStringDayOfWeek(int year, int week) {
		Date d = getLastDayOfWeek(year, week);
		return parseDate2String(d);
	}
	
	
	public static String getThisMonth(){
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		return c.get(Calendar.MONTH )+1+"";
	}
	
	public static String getThisYear(){
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		return c.get(Calendar.YEAR) + "";
	}
	/**
	 * cyq 2011-11-14添加
	 * 将日期字符串格式2011-11-14转换成字符串格式20111114
	 * @param t
	 * @return
	 */
	public static String parseStringToString(String t) {
		if(t == null || t.equals("")) {
			t = TimeUtil.getCurrentTime();
		}
		String[] times  = t.split("-");
		String tempendtime = "";
		for(String s : times) {
			tempendtime += s; 
		}
		return tempendtime;
	}
	
	/**
	 * 将数字格式的字符串转化为标准日期
	 * 如20111111转换成2011-11-11
	 * 如2011111111转换成2011-11-11 11
	 * @param t
	 * @return
	 */
	public static String parseNumberToString(String t) {
		if(t == null || t.equals("")) {
			t = TimeUtil.getCurrentTime();
		}
		String tempendtime = "";
		if(t.length() == 8){
			tempendtime = t.substring(0,4) + "-" + t.substring(4,6) + "-" + t.substring(6); 
		} else if(t.length() == 10)  {
			tempendtime = t.substring(0,4) + "-" + t.substring(4,6) + "-" + t.substring(6,8) + " " + t.substring(8);
		}
		return tempendtime;
	}

	/**
	 * Method name: getNextMonth <BR>
	 * Description:获取yyyyMM格式的当前时间的下个月。如当前时间是1988年3月，返回字符串"198804"
	 * 如当前时间是1988年1月，返回字符串"198901"<BR>
	 * Remark:zhangchen <BR>
	 * @param yearmonth 格式为yyyyMM
	 * @return  String<BR>
	 */
	public static String getNextMonth(String yearmonth){
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4));
		if (month == 12) {
			year++;
			month = 1;
		}else {
			month += 1;
		}
		return year + ((month + "").length() == 1 ? "0" + month : (month + ""));
	}
	
	/**
	 * Method name: getMonthByTimePeriod <BR>
	 * Description: 获取一个时间段内所包含的月份,若开始时间为2011-12-01，结束时间为2012-02-05 
	 * 则返回的list包含201112，201201，201202，且顺序从小到大<BR>
	 * Remark: zhangchen<BR>
	 * @param sdate 格式为yyyy-MM-dd
	 * @param edate  格式为yyyy-MM-dd
	 * @return  List<String><BR>
	 */
	public static List<String> getMonthByTimePeriod(String sdate,String edate){
		String s_date = sdate.replace("-", "").substring(0,6);
		String e_date = edate.replace("-", "").substring(0,6);
		List<String> yearmonths = new ArrayList<String>();
		while(!e_date.equals(s_date)){
			yearmonths.add(s_date);
			s_date = getNextMonth(s_date);
		}
		yearmonths.add(e_date);
		return yearmonths;
	}
	
	/**
	 * Method name: getMonthByMonth <BR>
	 * Description: 获取两个月份内所包含的月份,若开始时间为201112，结束时间为201202 
	 * 则返回的list包含201112，201201，201202，且顺序从小到大<BR>
	 * Remark: <BR>
	 * @param sdate 格式为yyyyMM
	 * @param edate  格式为yyyyMM
	 * @return  List<String><BR>
	 */
	public static List<String> getMonthByMonth(String sdate,String edate){
		List<String> yearmonths = new ArrayList<String>();
		while(!edate.equals(sdate)){
			yearmonths.add(sdate);
			sdate = getNextMonth(sdate);
		}
		yearmonths.add(edate);
		return yearmonths;
	}
	
	/**
	 * Method name: getDayByTimePeriod <BR>
	 * Description: 获取一个时间段内所包含的天，若开始时间为2012-06-30，结束时间为2012-07-02
	 * 则返回的list包含2012-06-30，2012-07-01，2012-07-02，且顺序从小到大 <BR>
	 * Remark: zhangchen<BR>
	 * @param sdate 格式为yyyy-MM-dd
	 * @param edate 格式为yyyy-MM-dd
	 * @return List<String> <BR>
	 * @throws ParseException  List<String><BR>
	 * 
	 */
	public static List<String> getDayByTimePeriod(String sdate,String edate) throws ParseException{
		Calendar sCalendar = Calendar.getInstance();
		Calendar eCalendar = Calendar.getInstance();
		List<String> days = new ArrayList<String>();
		sCalendar.setTime(parseString2Date(sdate));
		eCalendar.setTime(parseString2Date(edate));
		while(!sCalendar.equals(eCalendar)){
			days.add(parseCalendar2String(sCalendar,PATTERN));
			sCalendar.add(sCalendar.DATE, 1);
		}
		days.add(parseCalendar2String(eCalendar,PATTERN));
		return days;
	}
	
	/**
	 * 传入毫秒数和格式
	 * 返回格式化的时间字符串
	 * Method name: getFormatDateByLongMillis <BR>
	 * Description: getFormatDateByLongMillis <BR>
	 * Remark: <BR>
	 * @param millis
	 * @return  String<BR>
	 */
	public static String getFormatDateByLongMillis(long millis,String pattern){
		DateFormat fmt = getSimpleDateFormatInstance(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return fmt.format(calendar.getTime());
	}
	/**
	 * 
	 * Method name: getLongFromStringDate <BR>
	 * Description: getLongFromStringDate <BR>
	 * Remark: <BR>
	 * @param stime
	 * @param pattern
	 * @return
	 * @throws ParseException  Long<BR>
	 */
	public static Long getLongFromStringDate(String stime,String pattern) throws ParseException{
		SimpleDateFormat sdf = getSimpleDateFormatInstance(pattern);
		return sdf.parse(stime).getTime();
	}
	
	/**
	* 得到指定月的天数
	* */
	public static int getMonthDays(String yearmonth)
	{
		int year = Integer.parseInt(yearmonth.substring(0, 4));
		int month = Integer.parseInt(yearmonth.substring(4));
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}
	public static void  main(String[] args){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(new Date("2012020145".substring(0,8))));
	}

}