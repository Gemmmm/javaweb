package com.javaweb.service.system;

import com.javaweb.common.security.annotation.EnableCustomConfig;
import com.javaweb.common.security.annotation.EnableJWFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableJWFeignClients
@EnableCustomConfig
@SpringCloudApplication
@EnableResourceServer
public class JWSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWSystemApplication.class, args);
    }

}
