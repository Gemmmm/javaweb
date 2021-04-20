package com.javaweb.service.system.mapper;

import com.javaweb.api.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return
     */
    List<Role> getRolesByUserId(Integer userId);

}
