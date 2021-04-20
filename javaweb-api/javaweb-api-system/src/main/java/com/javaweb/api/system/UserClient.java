package com.javaweb.api.system;

import com.javaweb.api.system.entity.UserInfo;
import com.javaweb.api.system.fallback.UserClientFallback;
import com.javaweb.common.framework.utils.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "javaweb-system", fallback = UserClientFallback.class)
public interface UserClient {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/user/info/{username}")
    JsonResult<UserInfo> getInfoByName(@PathVariable("username") String username);

}
