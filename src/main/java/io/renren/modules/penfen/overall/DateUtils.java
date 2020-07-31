package io.renren.modules.penfen.overall;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : liukai
 * @date : 2020-06-28 17:12
 **/
public class DateUtils {

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, month);
        // 设置日历中月份的最后1天
        cal.set(Calendar.DATE, 0);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String getFirstDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, 0);
        // 设置日历中月份的第1天
        cal.set(Calendar.DAY_OF_MONTH, 1);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfYear = sdf.format(cal.getTime());
        return firstDayOfYear;
    }

    public static String getLastDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        // 设置年份
        cal.set(Calendar.YEAR, year);
        // 设置月份
        cal.set(Calendar.MONTH, 11);
        // 设置日历中月份的最后1天
        cal.set(Calendar.DATE, 0);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfYear = sdf.format(cal.getTime());
        return lastDayOfYear;
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String firstDayOfCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.MONTH, 0);//设置为1号,当前日期既为本月第一天
        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String getCurrentDate(String val) {
        SimpleDateFormat df = new SimpleDateFormat(val);//设置日期格式
        return df.format(new Date());
    }


    /**
     * 获取当前月最后一天
     *
     * @return
     */
    public static String lastDayOfCurrentMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(cal.getTime());
    }


    public static String compareDate(String firstDate, String lastDate) {
        String retDate = null;
        if (StringUtils.isEmpty(firstDate) && !StringUtils.isEmpty(lastDate)) {
            return lastDate;
        }
        if (!StringUtils.isEmpty(firstDate) && StringUtils.isEmpty(lastDate)) {
            return firstDate;
        }
        if (!StringUtils.isEmpty(firstDate) && !StringUtils.isEmpty(lastDate)) {
            // 格式化日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date first = sdf.parse(firstDate);
                Date last = sdf.parse(lastDate);
                if (first.after(last)) {
                    return sdf.format(first);
                } else {
                    return sdf.format(last);
                }
            } catch (ParseException e) {
            }
        }

        return retDate;
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取日期的月份
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1;
        if (month < 10) {
            return "0" + month;
        } else {
            return String.valueOf(month);
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @return
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 比较两个日期大小
     *
     * @param DATE1
     * @param DATE2
     * @param format 格式 yyyy-MM-dd,yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static int compareDate(String DATE1, String DATE2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 传入月份，将period转换为MM的格式
     *
     * @param period
     * @return
     */
    public static String getMonthTwoPlace(String period) {
        if (period.length() == 2) {
            return period;
        }

        if (period.length() == 1) {
            return "0" + period;
        }
        return null;
    }

    /**
     * 将日期字符串转化为Date类型
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date StringToDate(String dateStr, String pattern) {
        try {
            DateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将日期转化为字符串类型
     *
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        DateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 月份加一
     *
     * @param date
     * @return
     */
    public static String monthAddFrist(String date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM");
        try {
            Calendar ct = Calendar.getInstance();
            ct.setTime(df.parse(date));
            ct.add(Calendar.MONTH, +1);
            return df.format(ct.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

}