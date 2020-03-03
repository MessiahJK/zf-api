/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : Course.java 2020/02/21 23:21 MessiahJK
 */
@Data
public class Course {
    /**
     * 选课课号
     */
    private String courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程性质
     */
    private String courseNature;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 学分
     */
    private BigDecimal credit;

    /**
     * 周学时
     */
    private String hoursOfWeek;

    /**
     * 上课时间
     */
    private String courseTime;

    /**
     * 上课地点
     */
    private String courseLocation;

}
