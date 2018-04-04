package com.demo.angularpractice.account.util;

import org.joda.time.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 时间工具
 *
 * @author dzy
 */
@Component
public class DateTimeUtil {

    public static final String DATE_FORMATTER_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATTER_WEEK = "yyyy-MM-dd HH:mm:ss EE";
    public static final String DATE_FORMATTER_YYYY_MM_DD = "yyyy-MM-dd";


    /**
     * DateTime格式到String格式
     *
     * @param date
     * @return
     */
    public DateTime DateToDateTime(Date date) {
        return new DateTime(date);
    }

    /**
     * DateTime格式到String格式
     *
     * @param dateTime
     * @param pattern  格式
     * @return
     */
    public String DateTimeToString(DateTime dateTime, String pattern) {
        return dateTime.toString(pattern);
    }

    /**
     * String格式到DateTime格式
     *
     * @param dateTimeStr
     * @return
     */
    public DateTime StringToDateTime(String dateTimeStr) {
        return DateTime.parse(dateTimeStr);
    }

    /**
     * 格式化时间
     *
     * @param date    时间
     * @param pattern 格式
     * @return 字符串
     */
    public String getDateByFormatter(long date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    /**
     * 日期加天数
     *
     * @param date 时间
     * @param day  天数
     * @return
     */
    public DateTime plusDay(long date, int day) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(day);
    }

    /**
     * 日期加小时
     *
     * @param date  时间
     * @param hours 小时
     * @return
     */
    public DateTime plusHours(long date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours);
    }

    /**
     * 日期加分钟
     *
     * @param date    时间
     * @param minutes 分钟
     * @return
     */
    public DateTime plusMinutes(long date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes);
    }

    /**
     * 日期加年
     *
     * @param date  时间
     * @param years 年
     * @return
     */
    public DateTime plusYears(long date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years);
    }

    /**
     * 日期加周
     *
     * @param date  时间
     * @param weeks 周
     * @return
     */
    public DateTime plusWeeks(long date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks);
    }

    /**
     * 日期加月
     *
     * @param date   时间
     * @param months 月
     * @return
     */
    public DateTime plusMonths(long date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months);
    }

    /**
     * 日期加秒
     *
     * @param date   时间
     * @param millis 秒
     * @return
     */
    public DateTime plusMillis(long date, int millis) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMillis(millis);
    }

    /**
     * now
     *
     * @return
     */
    public DateTime now() {
        return DateTime.now();
    }

    /**
     * isBefore
     *
     * @param dateTime1
     * @param dateTime2
     * @return
     */
    public boolean isBefore(DateTime dateTime1, DateTime dateTime2) {
        return new DateTime(dateTime1).isBefore(dateTime2);
    }

    /**
     * isBeforeNow
     *
     * @param dateTime
     * @return
     */
    public boolean isBeforeNow(DateTime dateTime) {
        return new DateTime(dateTime).isBeforeNow();
    }

    /**
     * 计算区间毫秒数
     *
     * @param begin
     * @param end
     * @return
     */
    public long millisBetween(DateTime begin, DateTime end) {
        Duration d = new Duration(begin, end);
        long millis = d.getMillis();
        return millis;
    }

    /**
     * 计算目标时间是否在该区间内
     *
     * @param begin  开始时间
     * @param end    结束时间
     * @param target 目标时间
     * @return
     */
    public boolean isBetween(DateTime begin, DateTime end, DateTime target) {
        Interval interval = new Interval(begin, end);
        return interval.contains(target);
    }

    /**
     * 计算开始和结束时间之间相隔时间
     *
     * @param begin
     * @param end
     * @return
     */
    public String compare(DateTime begin, DateTime end) {
        int years = Years.yearsBetween(begin, end).getYears();
        int months = Months.monthsBetween(begin, end).getMonths();
        int days = Days.daysBetween(begin, end).getDays();
        int hours = Hours.hoursBetween(begin, end).getHours();
        int minutes = Minutes.minutesBetween(begin, end).getMinutes();
        int seconds = Seconds.secondsBetween(begin, end).getSeconds();

        return "活了" + years + "年" + months + "月" + days + "日" + hours + "小时" + minutes + "分钟" + seconds + "秒";
    }

}