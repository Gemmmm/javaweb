package com.javaweb.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JWEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JWEurekaApplication.class, args);
    }

}
