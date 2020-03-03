/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

import static us.betahouse.zfapi.zfhelper.constant.EventValue.*;

/**
 * @author MessiahJK
 * @version : CourseRequest.java 2020/02/22 1:19 MessiahJK
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseRequest extends CommonRequest{

    /**
     * 学年
     */
    private String academicYear;

    /**
     * 学期
     */
    private String semester;

    public Map<String,String> toMap(){
        Map<String,String> map=new HashMap<>(8);
        map.put(VIEW_STATE,viewState);
        map.put(EVENT_VALIDATION,eventValidation);
        map.put(EVENT_ARGUMENT,eventArgument);
        map.put(LAST_FOCUS,lastFocus);
        map.put(EVENT_TARGET, SEMESTER);
        map.put(ACADEMIC_YEAR,academicYear);
        map.put(SEMESTER,semester);
        return map;
    }
}
