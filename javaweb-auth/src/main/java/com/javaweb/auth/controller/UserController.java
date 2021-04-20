package com.javaweb.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 获取用户身份信息
 */
@RestController
@RequestMapping("/oauth")
public class UserController {

    /**
     * 获取用户身份信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
