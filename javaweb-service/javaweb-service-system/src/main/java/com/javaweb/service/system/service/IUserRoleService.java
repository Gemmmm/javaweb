package com.javaweb.service.system.service;

import com.javaweb.service.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 人员角色表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return
     */
    Set<String> getRolesByUserId(Integer userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户ID
     * @return
     */
    Set<String> getPermissionsByUserId(Integer userId);

    /**
     * 插入用户角色关系数据
     *
     * @param userId  用户ID
     * @param roleIds 角色ID
     */
    void insertUserRole(Integer userId, Integer[] roleIds);

    /**
     * 根据用户ID删除用户角色关系数据
     *
     * @param userId 用户ID
     */
    void deleteUserRole(Integer userId);

}
