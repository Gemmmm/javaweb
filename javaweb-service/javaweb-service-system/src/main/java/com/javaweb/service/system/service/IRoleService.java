package com.javaweb.service.system.service;

import com.javaweb.api.system.entity.Role;
import com.javaweb.common.framework.common.IBaseService;
import com.javaweb.common.framework.utils.JsonResult;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 获取角色列表
     *
     * @return
     */
    JsonResult getRoleList();

}
