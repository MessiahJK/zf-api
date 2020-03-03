/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.zfapi.dao.CourseRepository;
import us.betahouse.zfapi.zfhelper.ZfHelper;
import us.betahouse.zfapi.zfhelper.constant.IdentityType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author MessiahJK
 * @version : t1.java 2020/02/16 16:51 MessiahJK
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZfApiApplication.class)
public class t1 {

    @Autowired
    private CloseableHttpClient httpClient;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void t11() throws URISyntaxException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("124.160.107.91")
                .setPort(9090)
                .setPath("/").build();
        HttpGet httpGet = new HttpGet(uri);
            // 响应模型
            CloseableHttpResponse response = null;
            try {
                // 配置信息
                RequestConfig requestConfig = RequestConfig.custom()
                        // 设置连接超时时间(单位毫秒)
                        .setConnectTimeout(5000)
                        // 设置请求超时时间(单位毫秒)
                        .setConnectionRequestTimeout(5000)
                        // socket读写超时时间(单位毫秒)
                        .setSocketTimeout(5000)
                        // 设置是否允许重定向(默认为true)
                        .setRedirectsEnabled(true).build();
                // 将上面的配置信息 运用到这个Get请求里
                httpGet.setConfig(requestConfig);
                // 由客户端执行(发送)Get请求
                response = httpClient.execute(httpGet);
                // 从响应模型中获取响应实体
                HttpEntity responseEntity = response.getEntity();
                System.out.println("响应状态为:" + response.getStatusLine());
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    String body=EntityUtils.toString(responseEntity);
                    System.out.println("响应内容为:" + body);
                    Document doc = Jsoup.parse(body);
                    System.out.println("_____________");
                    System.out.println(doc.getElementById("__VIEWSTATE"));
                    System.out.println(doc.getElementById("__VIEWSTATE").attr("value"));
                    System.out.println(doc.getElementById("__EVENTVALIDATION"));
                }
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    @Test
    public void t2() throws Exception {
        ZfHelper zfHelper=new ZfHelper("17905219", "1qaz2wsx", IdentityType.IDENTITY_STUDENT, httpClient);
        System.out.println(JSON.toJSONString(zfHelper.getCourseList()));
    }

    @Test
    public void t3() throws Exception {
        ZfHelper zfHelper=new ZfHelper("17905219", "1qaz2wsx",IdentityType.IDENTITY_STUDENT, httpClient);
        System.out.println(JSON.toJSONString(zfHelper.getCourseList("2019-2020","1")));
    }
}
