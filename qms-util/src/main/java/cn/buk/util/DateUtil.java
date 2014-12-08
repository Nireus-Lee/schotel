/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/

package cn.buk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	
	private DateUtil() {}
	
	/**
	 * 按照指定的年月日数字生产Date对象
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static Date createDate(int year, int month, int date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, date);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期时间
	 * @return
	 */
	public static Date getCurDateTime() {
		return Calendar.getInstance().getTime();
	}
	
	public static String getCurDateTimeString() {
		return getCurDateTimeString(null);
	}
	
	public static String getCurDateTimeString(String format) {
		if (format == null) format = "yyyy年 MM月 dd日 HH时 mm分 ss秒";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(getCurDateTime());
	}


    public static String formatDate(Date date, String format, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

	public static String formatDate(Date date, String format) {
		if (format == null) format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getCurDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		return c.getTime();
	}
	
	public static Date getSomedayAfterToday(int x) {
		Calendar c = Calendar.getInstance();
		//c.clear(Calendar.HOUR_OF_DAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		c.add(Calendar.DAY_OF_MONTH, x);
		return c.getTime();		
	}
	
	public static Date addDays(Date date, int x) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.setTime(date);
		c.add(Calendar.DATE, x);
		return c.getTime();	
	}

    public static Date add(Date date, int x) {
        return addDays(date, x);
    }

    public static Date addMonth(Date date, int x) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(date);
        c.add(Calendar.MONTH, x);
        return c.getTime();
    }
	
	/**
	 * 获取完整的起飞时间
	 * @param dDate	起飞日期 yyyy-MM-dd
	 * @param dTime 起飞时间 HH24:mm
	 * @return
	 */
	public static Date getFullDDate(Date dDate, String dTime) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(dDate);
		
		int dHour =0, dMinute=0;
		
		try {
			dHour = Integer.parseInt(dTime.substring(0, 2));
			dMinute = Integer.parseInt(dTime.substring(3));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		c.set(Calendar.HOUR, dHour);
		c.set(Calendar.MINUTE, dMinute);
		
		return c.getTime();
	}

    public static Date getOnlyDate(Date date) {
        Calendar c = new GregorianCalendar();
        c.clear();

        c.setTime(date);
        c.clear(Calendar.HOUR);
        c.clear(Calendar.MINUTE);
        c.clear(Calendar.SECOND);
        c.clear(Calendar.MILLISECOND);

        return c.getTime();
    }



	public static boolean isGreaterThan0230() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR) >= 2 && c.get(Calendar.MINUTE) >= 30;
	}

    public static int getPastDays(Date thenDate) {
        return getPastDays(DateUtil.getCurDateTime(), thenDate);
    }

    public static int getPastDays(Date nowDate, Date thenDate) {
        long ms = nowDate.getTime()-thenDate.getTime();
        long seconds = ms/1000;
        long minutes = seconds/60;
        long hours = minutes/60;
        int days = (int)(hours/24);

        return days;
    }

	public static int getPastHours(Date pastDate) {
		long seconds = getPastTime(pastDate) /1000;

		int hours= (int) (seconds / 3600);

		return hours;
	}
	
	public static int getPastMinutes(Date pastDate) {
		long seconds = getPastTime(pastDate) / 1000;

		int minutes = (int) seconds / 60;
		
		return minutes;
	}

	/**
	 * 计算指定时刻距离当前的时间长短，单位为毫秒ms
	 * @param pastDate
     *
	 * @return
	 */
	public static int getPastTime(Date pastDate) {
		if (pastDate == null) return 0;

		long ms = (getCurDateTime().getTime() - pastDate.getTime());

		return (int)ms;
	}

	public static Date getDate(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.parse(dateString);
	}
	
	public static int getDayOfWeek(Date aDate) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(aDate);
		
		return c.get(Calendar.DAY_OF_WEEK);
		
	}
	
	public static String getDayOfWeekDesc(Date aDate) {
		Calendar c = new GregorianCalendar();
		c.clear();
		c.setTime(aDate);
		
		int x = c.get(Calendar.DAY_OF_WEEK);
		
		switch (x) 
		{
		case Calendar.SUNDAY: return "日";
		case Calendar.MONDAY: return "一";
		case Calendar.TUESDAY: return "二";
		case Calendar.WEDNESDAY: return "三";
		case Calendar.THURSDAY: return "四";
		case Calendar.FRIDAY: return "五";
		case Calendar.SATURDAY: return "六";
		default: return "";
		}		
		
	}
	
	/**
	 * 比较dateNow和dateThen的天数差, dateNow - dateThen
	 * @param dateNow
	 * @param dateThen
	 * @return
	 */
	public static int getDaySpan(Date dateNow, Date dateThen){
		long seconds = (dateNow.getTime() - dateThen.getTime() ) / 1000;
		 int days = (int)seconds / (60 * 60 * 24);
		 return days;
	}
    public static int getDaysSpan(Date dateNow, Date dateThen){
        return getDaySpan(dateNow, dateThen);
    }

    public static String getUpdateTimeDesc(Date updateTime) {
        long seconds = (getCurDateTime().getTime() - updateTime.getTime() ) / 1000;
        int minutes = (int) seconds / 60;

        if (minutes<0)
            return "时间还没到呢";
        else if (minutes<5)
            return "刚刚";
        else if (minutes >= 60) {
            int  hours = minutes / 60;
            return hours + "小时前";
        }
        else
            return minutes + "分钟前";
    }

	/*
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Date convertToDate(String val, String format) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(format);   
		return sdf.parse(val);  
	}
	
	public static Date convertToDate(String val) throws ParseException {
		return convertToDate(val, "yyyy-MM-dd");
	}
	
	public static Date convertToDateTime(String val) throws ParseException {
		return convertToDate(val, "yyyy-MM-dd hh:mm:ss");
	}
	
	
	public static String FormateDate(Date date, String format) {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return f.format(date);
	}

    public static boolean isValidateData(String val) {
        try {
            convertToDate(val, "yyyy-MM-dd");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public static String convertEtermDate(String val, String dayOfWeek, Date baseTime) {


        String day = val.substring(0, 2);
        String month = val.substring(2,5);
        if (month.compareToIgnoreCase("JAN") == 0)
            month = "01";
        else if (month.compareToIgnoreCase("FEB") == 0)
            month = "02";
        else if (month.compareToIgnoreCase("MAR") == 0)
            month = "03";
        else if (month.compareToIgnoreCase("APR") == 0)
            month = "04";
        else if (month.compareToIgnoreCase("MAY") == 0)
            month = "05";
        else if (month.compareToIgnoreCase("JUN") == 0)
            month = "06";
        else if (month.compareToIgnoreCase("JUL") == 0)
            month = "07";
        else if (month.compareToIgnoreCase("AUG") == 0)
            month = "08";
        else if (month.compareToIgnoreCase("SEP") == 0)
            month = "09";
        else if (month.compareToIgnoreCase("OCT") == 0)
            month = "10";
        else if (month.compareToIgnoreCase("NOV") == 0)
            month = "11";
        else if (month.compareToIgnoreCase("DEC") == 0)
            month = "12";
        else
            month = "00";

        if (val.length() == 7) {
            return "20" + val.substring(5) + "-" + month + "-" + day;
        }

        if (baseTime == null) baseTime = getCurDateTime();

        Calendar c = Calendar.getInstance();
        c.setTime(baseTime);

        int curMonth = c.get(Calendar.MONTH);
        int curYear = c.get(Calendar.YEAR);
        int calcYear = curYear;
        if (curMonth > Integer.parseInt(month))
            calcYear++;

        if (dayOfWeek != null && dayOfWeek.length() == 2) {
            try {
                int nextYear = curYear + 1;
                int prevYear = curYear - 1;
                boolean succeeded = false;

                Date calcDate = convertToDate(nextYear + "-" + month + "-" + day);
                String calcDayOfWeek = DateUtil.formatDate(calcDate, "E", Locale.ENGLISH);
                calcDayOfWeek = calcDayOfWeek.toUpperCase();
                if (calcDayOfWeek.indexOf(dayOfWeek) == 0) {
                    succeeded = true;
                    calcYear = nextYear;
                }

                if (succeeded == false) {
                    calcDate = convertToDate(curYear + "-" + month + "-" + day);
                    calcDayOfWeek = DateUtil.formatDate(calcDate, "E", Locale.ENGLISH);
                    calcDayOfWeek = calcDayOfWeek.toUpperCase();
                    if (calcDayOfWeek.indexOf(dayOfWeek) == 0) {
                        succeeded = true;
                        calcYear = curYear;
                    }
                }

                if (succeeded == false) {
                    calcDate = convertToDate(prevYear + "-" + month + "-" + day);
                    calcDayOfWeek = DateUtil.formatDate(calcDate, "E", Locale.ENGLISH);
                    calcDayOfWeek = calcDayOfWeek.toUpperCase();
                    if (calcDayOfWeek.indexOf(dayOfWeek) == 0) {
                        //successed = true;
                        calcYear = prevYear;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return Integer.toString(calcYear) + "-" + month + "-" + day;

    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }

    public static Date getDateOnMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.setTime(date);
        c.clear(Calendar.MILLISECOND);
        c.clear(Calendar.SECOND);
        return c.getTime();
    }
}
