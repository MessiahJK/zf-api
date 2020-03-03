/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.zfapi.zfhelper.utils;


import java.util.Calendar;
import java.util.Date;

/**
 * 学期工具
 *
 * @author dango.yxm
 * @version : TermUtil.java 2018/11/24 11:17 PM dango.yxm
 */
public class TermUtils {

    /**
     * 第一学期
     */
    private final static String FIRST_TERM = "1";

    /**
     * 第二学期
     */
    private final static String SECOND_TERM = "2";

    /**
     * 第一学期开始月份 9月
     */
    private final static int FIRST_TERM_START = Calendar.SEPTEMBER;

    /**
     * 第一学期结束月份 次年 2月
     */
    private final static int FIRST_TERM_END = Calendar.FEBRUARY;




    public static String getAcademicYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 需要月份补充
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // 月份小于第二学年的第一学期开始 就认为还在上学年 需要减1
        if (month < FIRST_TERM_START) {
            year = year - 1;
        }
        return year+"-"+(year+1);
    }
    public static String getSemester(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 需要月份补充
        int month = calendar.get(Calendar.MONTH);
        if (month >= FIRST_TERM_START || month < FIRST_TERM_END) {
            return FIRST_TERM;
        }
        return SECOND_TERM;
    }
    public static String getNowAcademicYear(){
        return getAcademicYear(new Date());
    }
    public static String getNowSemester(){
        return getSemester(new Date());
    }
}
