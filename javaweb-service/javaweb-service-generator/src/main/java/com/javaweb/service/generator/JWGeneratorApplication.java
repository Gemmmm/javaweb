package com.javaweb.service.generator;

import com.javaweb.common.security.annotation.EnableCustomConfig;
import com.javaweb.common.security.annotation.EnableJWFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableJWFeignClients
@EnableCustomConfig
@SpringCloudApplication
@EnableResourceServer
public class JWGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWGeneratorApplication.class, args);
    }

}
