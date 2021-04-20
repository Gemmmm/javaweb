package com.javaweb.service.system.service;

import com.javaweb.service.system.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 根据角色ID删除角色菜单关系数据
     *
     * @param roleId
     */
    boolean deleteRoleMenus(Integer roleId);

}
