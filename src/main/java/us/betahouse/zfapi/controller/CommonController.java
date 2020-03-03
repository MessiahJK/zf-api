/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.controller;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.zfapi.dao.TemporaryCourse;
import us.betahouse.zfapi.dao.CourseRepository;
import us.betahouse.zfapi.zfhelper.ZfHelper;
import us.betahouse.zfapi.zfhelper.bean.Course;
import us.betahouse.zfapi.zfhelper.constant.IdentityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : CommonController.java 2020/02/16 23:52 MessiahJK
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/common")
public class CommonController {
    private final Map<String, TemporaryCourse> map;
    private final CloseableHttpClient httpClient;
    @Autowired
    public CommonController(CourseRepository courseRepository, CloseableHttpClient httpClient) {
        map=new HashMap<>();
        List<TemporaryCourse> temporaryCourseList =courseRepository.findAll();
        for (TemporaryCourse temporaryCourse : temporaryCourseList) {
            map.put(temporaryCourse.getCourseId(), temporaryCourse);
        }
        this.httpClient = httpClient;
    }

    @PostMapping("")
    public List<TemporaryCourse> findCourse(String studentId, String password) throws Exception{
        List<TemporaryCourse> temporaryCourseList =new ArrayList<>();
        ZfHelper zfHelper=new ZfHelper(studentId, password, IdentityType.IDENTITY_STUDENT,httpClient);
        List<Course> courseIdList=zfHelper.getCourseList();
        for (Course s : courseIdList) {
            if(map.get(s.getCourseId())!=null) {
                temporaryCourseList.add(map.get(s.getCourseId()));
            }
        }
        return temporaryCourseList;
    }
}
