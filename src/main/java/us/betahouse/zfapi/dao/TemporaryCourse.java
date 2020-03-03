/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author MessiahJK
 * @version : Course.java 2020/02/16 23:02 MessiahJK
 */
@Data
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name="course")
public class TemporaryCourse {
    /**
     * 比赛id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 选课课号
     */
    private String courseId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教师名
     */
    private String teacherName;

    /**
     * 学分
     */
    private String credit;

    /**
     * 课程代码
     */
    private String courseCode;

    /**
     * 开课学院
     */
    private String college;

    /**
     * 上课时间
     */
    private String time;

    /**
     * 是否已在校级平台上建课
     */
    private String built;

    /**
     * 是否已上传课程资源（PPT、视频、题库、资料等）
     */
    private String uploadCourseResources;

    /**
     * 前三周拟采取的授课模式（直播、PPT速课、视频、PPT+线下讨论等）
     */
    private String method;

    /**
     * 备注（非校级平台建课的请注明其他平台网址，或注明其他线上教学新模式）
     */
    private String note;

    /**
     * qq群
     */
    private String qqGroup;

    /**
     * 是否停开或暂不开课
     */
    private String status;
}
