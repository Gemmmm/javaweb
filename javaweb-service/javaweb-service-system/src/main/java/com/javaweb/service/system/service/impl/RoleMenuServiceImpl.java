package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.service.system.entity.RoleMenu;
import com.javaweb.service.system.mapper.RoleMenuMapper;
import com.javaweb.service.system.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    /**
     * 根据角色ID删除角色菜单数据
     *
     * @param roleId
     */
    @Override
    public boolean deleteRoleMenus(Integer roleId) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        boolean result = remove(queryWrapper);
        return result;
    }
}
