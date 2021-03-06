package com.javaweb.gateway.config;

import com.javaweb.gateway.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }

}
