/*
  betahouse.us
  CopyRight (c) 2012 - 2020
 */
package us.betahouse.zfapi.zfhelper.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author MessiahJK
 * @version : HttpClientClose.java 2020/02/16 22:18 MessiahJK
 */
@Component
public class HttpClientClose extends Thread {
    private final PoolingHttpClientConnectionManager manage;
    private volatile boolean shutdown;
    //开关 volatile表示多线程可变数据,一个线程修改,其他线程立即修改

    public HttpClientClose(PoolingHttpClientConnectionManager manage) {
        //线程开启启动
        this.start();
        this.manage = manage;
    }


    @Override
    public void run() {
        try {
            //如果服务没有关闭,执行线程
            while (!shutdown) {
                synchronized (this) {
                    //每五秒执行一次
                    wait(5000);
                    //关闭超时的链接
                    manage.closeExpiredConnections();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        super.run();
    }

    /**
     * 关闭清理无效连接的线程
     * 容器关闭时执行该方法.
     */
    @PreDestroy
    public void shutdown() {
        shutdown = true;
        synchronized (this) {

            notifyAll();
            //全部从等待中唤醒.执行关闭操作;
        }
    }
}