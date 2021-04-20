package com.javaweb.service.system.dto;

import lombok.Data;

/**
 * 更新密码Dto
 */
@Data
public class UpdatePwdDto {

    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 密码
     */
    private String password;

}
