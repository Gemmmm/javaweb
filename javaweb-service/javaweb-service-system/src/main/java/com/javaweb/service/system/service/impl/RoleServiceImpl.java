package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.api.system.entity.Role;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.DateUtils;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.entity.RoleMenu;
import com.javaweb.service.system.mapper.RoleMapper;
import com.javaweb.service.system.query.RoleQuery;
import com.javaweb.service.system.service.IRoleMenuService;
import com.javaweb.service.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private Validator validator;
    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 获取分页数据
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        RoleQuery roleQuery = (RoleQuery) query;
        // 查询条件
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        // 角色名称
        if (!StringUtils.isEmpty(roleQuery.getName())) {
            queryWrapper.like("name", roleQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getSize());
        IPage<Role> pageData = roleMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Role entity) {
        // 字段校验
        Set<ConstraintViolation<Role>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Role> item : violationSet) {
            return JsonResult.error(item.getMessage());
        }
        boolean result = false;
        if (entity.getId() != null && entity.getId() > 0) {
            // 编辑
            entity.setUpdateUser(SecurityUtils.getUserId());
            entity.setUpdateTime(DateUtils.now());
            result = this.updateById(entity);
        } else {
            // 添加
            Integer count = roleMapper.selectCount(new LambdaQueryWrapper<Role>()
                    .eq(Role::getName, entity.getName())
                    .eq(Role::getMark, 1));
            if (count > 0) {
                return JsonResult.error("系统中已存在相同的角色名称");
            }
            entity.setCreateUser(SecurityUtils.getUserId());
            entity.setCreateTime(DateUtils.now());
            entity.setMark(1);
            result = this.save(entity);
        }
        if (!result) {
            return JsonResult.error("操作失败");
        }
        // 同步存入角色菜单关系数据
        boolean isDel = roleMenuService.deleteRoleMenus(entity.getId());
        if (!isDel) {
//            return JsonResult.error("原始角色菜单数据删除失败");
        }
        // 插入新的角色菜单关系数据
        List<RoleMenu> roleMenuList = new ArrayList<>();
        if (!StringUtils.isEmpty(entity.getMenuIds())) {
            for (String menuId : entity.getMenuIds()) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(entity.getId());
                roleMenu.setMenuId(Integer.valueOf(menuId));
                roleMenuList.add(roleMenu);
            }
        }
        // 批量插入角色菜单关系数据
        roleMenuService.saveBatch(roleMenuList);
        return JsonResult.success("操作成功");
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @Override
    public JsonResult getRoleList() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");
        List<Role> roleList = roleMapper.selectList(queryWrapper);
        return JsonResult.success(roleList);
    }
}
