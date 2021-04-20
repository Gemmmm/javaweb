package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.api.system.entity.Role;
import com.javaweb.api.system.entity.User;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.service.system.entity.Menu;
import com.javaweb.service.system.entity.UserRole;
import com.javaweb.service.system.mapper.MenuMapper;
import com.javaweb.service.system.mapper.RoleMapper;
import com.javaweb.service.system.mapper.UserRoleMapper;
import com.javaweb.service.system.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 人员角色表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public Set<String> getRolesByUserId(Integer userId) {
        Set<String> roles = new HashSet<>();
        if (User.isAdmin(userId)) {
            roles.add("admin");
        } else {
            List<Role> roleList = roleMapper.getRolesByUserId(userId);
            if (StringUtils.isNotNull(roleList)) {
                for (Role role : roleList) {
                    if (StringUtils.isNotNull(role)) {
                        if (StringUtils.isNotEmpty(role.getTag())) {
                            roles.addAll(Arrays.asList(role.getTag().trim().split(",")));
                        }
                    }
                }
            }
        }
        return roles;
    }

    /**
     * 根据用户ID获取用户权限信息
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public Set<String> getPermissionsByUserId(Integer userId) {
        Set<String> permissionSets = new HashSet<>();
        if (User.isAdmin(userId)) {
            permissionSets.add("*:*:*");
        } else {
            List<Menu> menuList = menuMapper.getPermissionsListByUserId(userId);
            if (StringUtils.isNotNull(menuList)) {
                for (Menu menu : menuList) {
                    if (StringUtils.isNotEmpty(menu.getPermission())) {
                        permissionSets.addAll(Arrays.asList(menu.getPermission().trim().split(",")));
                    }
                }
            }
        }
        return permissionSets;
    }

    /**
     * 插入用户角色关系数据
     *
     * @param userId  用户ID
     * @param roleIds 角色ID
     */
    @Override
    public void insertUserRole(Integer userId, Integer[] roleIds) {
        if (StringUtils.isNotNull(roleIds)) {
            List<UserRole> userRoleList = new ArrayList<>();
            for (Integer roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleList.add(userRole);
            }
            if (userRoleList.size() > 0) {
                userRoleService.saveBatch(userRoleList);
            }
        }
    }

    /**
     * 根据用户ID删除用户角色数据
     *
     * @param userId 用户ID
     */
    @Override
    public void deleteUserRole(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        remove(queryWrapper);
    }
}
