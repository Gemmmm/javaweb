package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.api.system.entity.User;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.entity.Menu;
import com.javaweb.service.system.mapper.MenuMapper;
import com.javaweb.service.system.query.MenuQuery;
import com.javaweb.service.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        MenuQuery menuQuery = (MenuQuery) query;
        // 查询条件
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        // 菜单名称
        if (!StringUtils.isEmpty(menuQuery.getName())) {
            queryWrapper.like("name", menuQuery.getName());
        }
        queryWrapper.le("type", 3);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Menu> menuList = list(queryWrapper);
        return JsonResult.success(menuList);
    }

    /**
     * 根据上级ID获取子级菜单
     *
     * @param pid 上级ID
     * @return
     */
    @Override
    public List<Menu> getListByPid(Integer pid) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid);
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Menu> menuList = list(queryWrapper);
        return menuList;
    }

    /**
     * 获取菜单路由列表
     *
     * @return
     */
    @Override
    public JsonResult getMenuRoutesList() {
        List<Menu> menuList = null;
        Integer userId = SecurityUtils.getLoginUser().getUserId();
        if (User.isAdmin(userId)) {
            menuList = menuMapper.getPermissionsAll();
        } else {
            menuList = menuMapper.getPermissionsListByUserId(userId);
        }
        return JsonResult.success(menuList);
    }

    /**
     * 根据角色ID获取角色菜单权限
     *
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) {
        return menuMapper.getMenuListByRoleId(roleId);
    }
}
