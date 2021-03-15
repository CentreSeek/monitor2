package com.vivalnk.sdk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

  public static String sPattern = "yyyy-MM-dd HH:mm:ss.SSS";
  public static String sPatternFileName = "yyyy-MM-dd HH:mm:ss.SSS";

  static final ThreadLocal<SimpleDateFormat> sThreadLocal = new ThreadLocal<>();

  private static SimpleDateFormat myDateFormat() {
    if (sThreadLocal.get() == null) {
      sThreadLocal.set(new SimpleDateFormat(sPattern));
    }
    return sThreadLocal.get();
  }

  public static String format(Date date, String pattern) {
    myDateFormat().applyPattern(pattern);
    return myDateFormat().format(date);
  }

  public static String format(long timeMillis, String pattern) {
    return format(new Date(timeMillis), pattern);
  }

  public static String format(long timeMillis) {
    return format(new Date(timeMillis));
  }

  public static String format(Date date) {
    myDateFormat().applyPattern(sPattern);
    return myDateFormat().format(date);
  }

  public static long parse(String time, String pattern) throws ParseException {
    myDateFormat().applyPattern(pattern);
    return myDateFormat().parse(time).getTime();
  }

}
