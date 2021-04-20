package com.javaweb.api.system.fallback;

import com.javaweb.api.system.UserClient;
import com.javaweb.api.system.entity.UserInfo;
import com.javaweb.common.framework.utils.JsonResult;
import org.springframework.stereotype.Component;

/**
 * 用户服务调用失败处理
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public JsonResult<UserInfo> getInfoByName(String username) {
        return JsonResult.error("用户服务调用失败");
    }
}
