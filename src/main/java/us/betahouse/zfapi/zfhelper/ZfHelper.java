/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.betahouse.zfapi.zfhelper.bean.Course;
import us.betahouse.zfapi.zfhelper.bean.CourseRequest;
import us.betahouse.zfapi.zfhelper.bean.LoginRequest;
import us.betahouse.zfapi.zfhelper.bean.builder.CourseBuilder;
import us.betahouse.zfapi.zfhelper.bean.builder.CourseRequestBuilder;
import us.betahouse.zfapi.zfhelper.bean.builder.LoginRequestBuilder;
import us.betahouse.zfapi.zfhelper.constant.IdentityType;
import us.betahouse.zfapi.zfhelper.utils.TermUtils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

import static org.apache.http.HttpStatus.SC_MOVED_TEMPORARILY;
import static us.betahouse.zfapi.zfhelper.constant.EventValue.EVENT_VALIDATION;
import static us.betahouse.zfapi.zfhelper.constant.EventValue.VIEW_STATE;

/**
 * @author MessiahJK
 * @version : ZFHelper.java 2020/02/16 17:19 MessiahJK
 */
public class ZfHelper {
    /**
     * 编码
     */
    private static final String ENCODING = "UTF-8";


    /**
     * 用户id
     */
    private String userId;

    /**
     * 请求参数
     */
    private static RequestConfig REQUEST_CONFIG;

    /**
     * 响应模型
     */
    private CloseableHttpResponse response;

    /**
     * 响应实体
     */
    private HttpEntity responseEntity;

    /**
     * HttpClient执行状态
     */
    private HttpClientContext context;

    /**
     * httpClient操作类
     */
    private CloseableHttpClient httpClient;

    //请求URL
    /**
     * 基础URL
     */
    private static final String BASE_URL = "http://124.160.107.91:9090";
    /**
     * 登录URL
     */
    private static final String LOGIN_URL = BASE_URL + "/default2.aspx";
    /**
     * 主页URL模板
     */
    private static final String INDEX_URI_TEMPLATE = BASE_URL + "/xs_main.aspx?xh={0}";
    /**
     * 课程URL模板
     */
    private static final String COURSE_SELECTION_URL_TEMPLATE = BASE_URL + "/xsxkqk.aspx?xh={0}&xm=%BD%AF%BF%C1&gnmkdm=N121101";

    public ZfHelper(String userId, String passWord,IdentityType identityType, CloseableHttpClient httpClient) throws Exception {

        REQUEST_CONFIG = RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();
        this.userId = userId;
        this.httpClient = httpClient;

        CookieStore cookieStore = new BasicCookieStore();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
        if (!this.login(userId, passWord,identityType)) {
            throw new NullPointerException("登录失败");
        }
    }

    /**
     * 登录
     *
     * @param userId   用户id
     * @param passWord 密码
     * @return 是否成功登录
     * @throws Exception 异常
     */
    private Boolean login(String userId, String passWord, IdentityType identityType) throws Exception {
        HttpGet httpGet = newHttpGet(LOGIN_URL);
        // 由客户端执行(发送)Get请求 获取登录界面
        response = httpClient.execute(httpGet, context);
        // 从响应模型中获取响应实体
        responseEntity = response.getEntity();
        String stringResponseEntity = EntityUtils.toString(responseEntity);
        // 将实体转化为Jsoup对象
        Document doc = Jsoup.parse(stringResponseEntity);
        //获取登录界面的视图状态和事件验证值
        String viewState = doc.getElementById(VIEW_STATE).attr("value");
        String eventValidation = doc.getElementById(EVENT_VALIDATION).attr("value");
        //构建登录数据
        LoginRequest loginRequest = LoginRequestBuilder.aLoginData()
                .withViewState(viewState)
                .withEventValidation(eventValidation)
                .withUserId(userId)
                .withPassword(passWord)
                .withIdentity(identityType.getValue())
                .build();

        //构建登录post请求
        HttpPost httpPost = newHttpPost(LOGIN_URL);
        packageParam(loginRequest.toMap(), httpPost);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        response = httpClient.execute(httpPost, context);
        //返回是否成功登陆
        return SC_MOVED_TEMPORARILY == (response.getStatusLine().getStatusCode());
    }

    public List<Course> getCourseList() throws IOException {
        return getCourseList(TermUtils.getNowAcademicYear(), TermUtils.getNowSemester());
    }

    public List<Course> getCourseList(String academicYear,String semester) throws IOException {
        String courseSelectionUrl = MessageFormat.format(COURSE_SELECTION_URL_TEMPLATE, userId);
        HttpGet httpGet = newHttpGet(courseSelectionUrl);
        httpGet.setHeader("Referer", courseSelectionUrl);
        response = httpClient.execute(httpGet, context);
        responseEntity = response.getEntity();
        String stringResponseEntity = EntityUtils.toString(responseEntity);
        // 将实体转化为Jsoup对象
        Document doc = Jsoup.parse(stringResponseEntity);
        //获取登录界面的视图状态、事件验证值、事件理由/主题、最后焦点
        String viewState = doc.getElementById(VIEW_STATE).attr("value");
        String eventValidation = doc.getElementById(EVENT_VALIDATION).attr("value");
        String eventArgument="";
        String lastFocus="";

        CourseRequest courseRequest= CourseRequestBuilder.aCourseRequest()
                .withAcademicYear(academicYear)
                .withSemester(semester)
                .withViewState(viewState)
                .withEventValidation(eventValidation)
                .withEventArgument(eventArgument)
                .withLastFocus(lastFocus)
                .build();

        HttpPost httpPost=newHttpPost(courseSelectionUrl);
        packageParam(courseRequest.toMap(), httpPost);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Referer", courseSelectionUrl);
        response = httpClient.execute(httpPost, context);
        responseEntity = response.getEntity();
        stringResponseEntity = EntityUtils.toString(responseEntity);
        // 将实体转化为Jsoup对象
        doc = Jsoup.parse(stringResponseEntity);
        List<Course> result = new ArrayList<>();
        Elements hang = doc.select("table").select("tr");
        int rows = hang.size();
        for (int i = 1; i < rows; i++) {
            Elements lie = hang.get(i).select("td");
            Course course= CourseBuilder.aCourse()
                    .withCourseId(lie.get(0).text())
                    .withCourseName(lie.get(1).text())
                    .withCourseNature(lie.get(2).text())
                    .withTeacherName(lie.get(4).text())
                    .withCredit(new BigDecimal(lie.get(5).text()))
                    .withHoursOfWeek(lie.get(6).text())
                    .withCourseTime(lie.get(7).text())
                    .withCourseLocation(lie.get(8).text())
                    .build();
            result.add(course);
        }
        return result;
    }

    /**
     * 封装参数到网络请求中
     *
     * @param params     参数map
     * @param httpMethod 网络请求类型 如post get
     * @throws UnsupportedEncodingException 异常
     */
    private static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nameValuePairList, ENCODING));
        }
    }

    private HttpGet newHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(REQUEST_CONFIG);
        return httpGet;
    }

    private HttpPost newHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(REQUEST_CONFIG);
        return httpPost;
    }


}
