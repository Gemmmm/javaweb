package com.javaweb.service.system.mapper;

import com.javaweb.service.system.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单管理表 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户ID获取权限列表
     *
     * @param userId 用户ID
     * @return
     */
    List<Menu> getPermissionsListByUserId(Integer userId);

    /**
     * 获取所有权限
     *
     * @return
     */
    List<Menu> getPermissionsAll();

    /**
     * 根据角色ID获取菜单权限
     *
     * @param roleId
     * @return
     */
    List<Menu> getMenuListByRoleId(Integer roleId);

}
