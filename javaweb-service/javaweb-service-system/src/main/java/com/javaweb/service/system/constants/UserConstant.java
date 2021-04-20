package com.javaweb.service.system.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户管理 模块常量
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-04-20
 */
public class UserConstant {

    /**
     * 性别
     */
    public static Map<Integer, String> USER_GENDER_LIST = new HashMap<Integer, String>() {
        {
            put(1, "男");
            put(2, "女");
            put(3, "保密");
        }
    };
    /**
     * 状态
     */
    public static Map<Integer, String> USER_STATUS_LIST = new HashMap<Integer, String>() {
        {
            put(1, "正常");
            put(2, "禁用");
        }
    };
}