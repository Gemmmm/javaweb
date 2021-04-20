package com.javaweb.auth.event;

import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.entity.LoginUser;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * 认证成功监听
 */
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    /**
     * 事件处理
     *
     * @param authenticationSuccessEvent
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = (Authentication) authenticationSuccessEvent.getSource();
        if (StringUtils.isNotEmpty(authentication.getAuthorities())
                && authentication.getPrincipal() instanceof LoginUser) {
            LoginUser user = (LoginUser) authentication.getPrincipal();

            String username = user.getUsername();
//
//            // 记录用户登录日志
//            remoteLogService.saveLogininfor(username, CommonConstants.LOGIN_SUCCESS, "登录成功");
        }
    }
}
