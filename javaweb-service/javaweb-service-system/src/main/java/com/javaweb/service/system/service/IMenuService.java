package com.javaweb.service.system.service;

import com.javaweb.common.framework.common.IBaseService;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Menu;

import java.util.List;

/**
 * <p>
 * 菜单管理表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 获取菜单路由列表
     *
     * @return
     */
    JsonResult getMenuRoutesList();

    /**
     * 根据角色ID获取菜单
     *
     * @param roleId 角色ID
     * @return
     */
    List<Menu> getMenuListByRoleId(Integer roleId);

    /**
     * 根据上级ID获取子级菜单
     *
     * @param pid 上级ID
     * @return
     */
    List<Menu> getListByPid(Integer pid);

}
