/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean.builder;

import us.betahouse.zfapi.zfhelper.bean.Course;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : CourseBuilder.java 2020/02/22 0:19 MessiahJK
 */
public final class CourseBuilder {
    private String courseId;
    private String courseName;
    private String courseNature;
    private String teacherName;
    private BigDecimal credit;
    private String hoursOfWeek;
    private String courseTime;
    private String courseLocation;

    private CourseBuilder() {
    }

    public static CourseBuilder aCourse() {
        return new CourseBuilder();
    }

    public CourseBuilder withCourseId(String courseId) {
        this.courseId = courseId;
        return this;
    }

    public CourseBuilder withCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public CourseBuilder withCourseNature(String courseNature) {
        this.courseNature = courseNature;
        return this;
    }

    public CourseBuilder withTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public CourseBuilder withCredit(BigDecimal credit) {
        this.credit = credit;
        return this;
    }

    public CourseBuilder withHoursOfWeek(String hoursOfWeek) {
        this.hoursOfWeek = hoursOfWeek;
        return this;
    }

    public CourseBuilder withCourseTime(String courseTime) {
        this.courseTime = courseTime;
        return this;
    }

    public CourseBuilder withCourseLocation(String courseLocation) {
        this.courseLocation = courseLocation;
        return this;
    }

    public Course build() {
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName(courseName);
        course.setCourseNature(courseNature);
        course.setTeacherName(teacherName);
        course.setCredit(credit);
        course.setHoursOfWeek(hoursOfWeek);
        course.setCourseTime(courseTime);
        course.setCourseLocation(courseLocation);
        return course;
    }
}
