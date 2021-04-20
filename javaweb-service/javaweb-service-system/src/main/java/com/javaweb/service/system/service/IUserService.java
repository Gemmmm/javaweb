package com.javaweb.service.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaweb.api.system.entity.User;
import com.javaweb.api.system.entity.UserInfo;
import com.javaweb.common.framework.common.IBaseService;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.dto.UpdatePwdDto;
import com.javaweb.service.system.query.UserQuery;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-09-18
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return
     */
    JsonResult<UserInfo> getInfoByName(String username);

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return
     */
    JsonResult resetPwd(Integer userId);

    /**
     * 更新密码
     *
     * @param updatePwdDto 参数
     * @return
     */
    JsonResult updatePwd(UpdatePwdDto updatePwdDto);

}
