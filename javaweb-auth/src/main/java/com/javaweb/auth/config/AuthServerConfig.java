package com.javaweb.auth.config;

import com.javaweb.auth.exception.CustomWebResponseExceptionTranslator;
import com.javaweb.common.framework.constants.CacheConstants;
import com.javaweb.common.security.constants.SecurityConstants;
import com.javaweb.common.security.entity.LoginUser;
import com.javaweb.common.security.service.RedisClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private TokenEnhancer tokenEnhancer;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.allowFormAuthenticationForClients() // ?????????????????????client_id???client_secret??????token
//                .checkTokenAccess("permitAll()");
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // ????????????
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // ??????token????????????
                .tokenStore(tokenStore())
                // ?????????????????????
                .tokenEnhancer(tokenEnhancer)
                // ????????????????????????
                .userDetailsService(userDetailsService)
                // ?????????????????????
                .authenticationManager(authenticationManager)
                // ?????????????????? refresh_token
                .reuseRefreshTokens(false)
                // ?????????????????????
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());
    }

    /**
     * ??????ClientDetails??????
     */
    public RedisClientDetailsService clientDetailsService() {
        RedisClientDetailsService clientDetailsService = new RedisClientDetailsService(dataSource);
        return clientDetailsService;
    }

    /**
     * ??????Token????????????(???????????????Redis???)
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }

    /**
     * ?????????????????????
     *
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (authentication.getUserAuthentication() != null) {
                Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();
                LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }

}
