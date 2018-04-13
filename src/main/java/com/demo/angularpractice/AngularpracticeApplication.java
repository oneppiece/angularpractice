package com.demo.angularpractice;

import com.demo.angularpractice.init.ApplicationStartup;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author dzy
 */
@SpringBootApplication
@EnableCaching
@EnableRedisHttpSession
@MapperScan({"com.demo.angularpractice.repository", "com.demo.angularpractice.account"})
public class AngularpracticeApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AngularpracticeApplication.class);
        springApplication.addListeners(new ApplicationStartup());
        springApplication.run(args);
    }
}
