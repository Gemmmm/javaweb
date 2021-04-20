package com.javaweb.auth;

import com.javaweb.common.security.annotation.EnableCustomConfig;
import com.javaweb.common.security.annotation.EnableJWFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableCustomConfig
@EnableJWFeignClients
@SpringCloudApplication
@EnableAuthorizationServer
public class JWAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWAuthApplication.class, args);
    }

}
