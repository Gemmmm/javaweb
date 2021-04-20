package com.javaweb.common.security.utils;

import com.javaweb.common.security.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SecurityUtils {

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Integer getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取用户认证信息
     *
     * @return
     */
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getLoginUser(authentication);
    }

    /**
     * 获取认证信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户登录信息
     *
     * @param authentication
     * @return
     */
    public static LoginUser getLoginUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser) {
            return (LoginUser) principal;
        }
        return null;
    }

    /**
     * 生成加密密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 比对密码是否一直
     *
     * @param password        原始密码
     * @param encodedPassword 加密后的密码
     * @return
     */
    public static boolean matchesPassword(String password, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, encodedPassword);
    }

}
