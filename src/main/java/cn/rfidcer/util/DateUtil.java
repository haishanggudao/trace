package cn.rfidcer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import cn.rfidcer.dto.TimeRange;

/**
 * 日期Util类 
 * @author jie.jia
 *
 */
public class DateUtil {
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";  
	  
    /** 
     * 获得默认的 date pattern 
     */  
    public static String getDatePattern()  
    {  
        return defaultDatePattern;  
    }  
  
    /** 
     * 返回预设Format的当前日期字符串 
     */  
    public static String getToday()  
    {  
        Date today = new Date();  
        return format(today);  
    }  
  
    /** 
     * 使用预设Format格式化Date成字符串 
     */  
    public static String format(Date date)  
    {  
        return date == null ? " " : format(date, getDatePattern());  
    }  
  
    /** 
     * 获取时间戳
     */  
    public static String timestamp()  
    {  
    	return DateUtil.format(new Date(),"yyyyMMddHHmmss");
    }  
    /** 
     * 获取时间戳
     */  
    public static String dateTime()  
    {  
    	return DateUtil.format(new Date(),"yyyy-MM-dd");
    }      
    
    /** 
     * 使用参数Format格式化Date成字符串 
     */  
    public static String format(Date date, String pattern)  
    {  
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);  
    }  
    
    
    /** 
     * 使用预设格式将字符串转为Date 
     */  
    public static Date parse(String strDate) throws ParseException  
    {  
        return StringUtils.isBlank(strDate) ? null : parse(strDate,  
                getDatePattern());  
    }  
  
    /** 
     * 使用参数Format将字符串转为Date 
     */  
    public static Date parse(String strDate, String pattern)  
            throws ParseException  
    {  
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(  
                pattern).parse(strDate);  
    }  
  
    /** 
     * 在日期上增加数个整月 
     */  
    public static Date addMonth(Date date, int n)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.MONTH, n);  
        return cal.getTime();  
    }  
  
    public static String getLastDayOfMonth(String year, String month)  
    {  
        Calendar cal = Calendar.getInstance();  
        // 年  
        cal.set(Calendar.YEAR, Integer.parseInt(year));  
        // 月，因为Calendar里的月是从0开始，所以要-1  
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);  
        // 日，设为一号  
        cal.set(Calendar.DATE, 1);  
        // 月份加一，得到下个月的一号  
        cal.add(Calendar.MONTH, 1);  
        // 下一个月减一为本月最后一天  
        cal.add(Calendar.DATE, -1);  
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号  
    }  
  
    
    // 获得当前日期与本周一相差的天数
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    } 
    // 获得当前周- 周一的日期
    public static  String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday = format(monday,"yyyy-MM-dd");
        return preMonday;
    }
    
    // 获得当前周- 周日  的日期
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        String preMonday = format(monday,"yyyy-MM-dd");
        return preMonday;
    }



    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



    // 获得当前月--开始日期
    public static String getMinMonthDate(String date) {   
             Calendar calendar = Calendar.getInstance();   
              try {
                 calendar.setTime(dateFormat.parse(date));
                 calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
                 return dateFormat.format(calendar.getTime());
               } catch (java.text.ParseException e) {
               e.printStackTrace();
              }
            return null;
    }



    // 获得当前月--结束日期
    public static String getMaxMonthDate(String date){   
	         Calendar calendar = Calendar.getInstance();   
	         try {
	                calendar.setTime(dateFormat.parse(date));
	                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	                return dateFormat.format(calendar.getTime());
	         }  catch (java.text.ParseException e) {
	                e.printStackTrace();
	          }
	        return null;
	}
    
    
    public static Date getDate(String year, String month, String day)  
            throws ParseException  
    {  
        String result = year + "- "  
                + (month.length() == 1 ? ("0 " + month) : month) + "- "  
                + (day.length() == 1 ? ("0 " + day) : day);  
        return parse(result);  
    }  
    
    /** 获取时间范围字符串
     * @param dateType 1-本日;2-本周;3-本月
     * @return
     */
    public static TimeRange getTimeRangeByDateType(int dateType){
    	TimeRange timeRange = new TimeRange();
    	String startTime=null;
    	String endTime=null;
    	switch(dateType){
			case 1:
				startTime= DateUtil.dateTime()+" 00:00:00";
				endTime= DateUtil.dateTime()+" 23:59:59";
				break;
			case 2:
				startTime= DateUtil.getCurrentMonday()+" 00:00:00";
				endTime= DateUtil.getPreviousSunday()+" 23:59:59";
				break;
			case 3:
				startTime= DateUtil.getMinMonthDate(DateUtil.getToday())+" 00:00:00";
				endTime= DateUtil.getMaxMonthDate(DateUtil.getToday())+" 23:59:59";
				break;				
			default:
				startTime= DateUtil.getCurrentMonday()+" 00:00:00";
				endTime= DateUtil.getPreviousSunday()+" 23:59:59";
		}
    	timeRange.setStartTime(startTime);
    	timeRange.setEndTime(endTime);
    	return timeRange;
    }
}
