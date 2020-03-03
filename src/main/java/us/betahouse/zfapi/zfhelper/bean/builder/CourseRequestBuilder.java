/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean.builder;

import us.betahouse.zfapi.zfhelper.bean.CourseRequest;

/**
 * @author MessiahJK
 * @version : CourseRequestBuilder.java 2020/02/22 1:51 MessiahJK
 */
public final class CourseRequestBuilder {
    protected String viewState;
    protected String eventValidation;
    protected String eventArgument;
    protected String lastFocus;
    protected String eventTarget;
    private String academicYear;
    private String semester;

    private CourseRequestBuilder() {
    }

    public static CourseRequestBuilder aCourseRequest() {
        return new CourseRequestBuilder();
    }

    public CourseRequestBuilder withViewState(String viewState) {
        this.viewState = viewState;
        return this;
    }

    public CourseRequestBuilder withEventValidation(String eventValidation) {
        this.eventValidation = eventValidation;
        return this;
    }

    public CourseRequestBuilder withEventArgument(String eventArgument) {
        this.eventArgument = eventArgument;
        return this;
    }

    public CourseRequestBuilder withAcademicYear(String academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    public CourseRequestBuilder withLastFocus(String lastFocus) {
        this.lastFocus = lastFocus;
        return this;
    }

    public CourseRequestBuilder withSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public CourseRequestBuilder withEventTarget(String eventTarget) {
        this.eventTarget = eventTarget;
        return this;
    }

    public CourseRequest build() {
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setViewState(viewState);
        courseRequest.setEventValidation(eventValidation);
        courseRequest.setEventArgument(eventArgument);
        courseRequest.setAcademicYear(academicYear);
        courseRequest.setLastFocus(lastFocus);
        courseRequest.setSemester(semester);
        courseRequest.setEventTarget(eventTarget);
        return courseRequest;
    }
}
