package us.betahouse.zfapi;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author MessiahJK
 */
@SpringBootApplication
public class ZfApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ZfApiApplication.class, args);
    }

}
