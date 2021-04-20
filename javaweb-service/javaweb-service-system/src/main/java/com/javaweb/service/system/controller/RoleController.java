package com.javaweb.service.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.api.system.entity.Role;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Menu;
import com.javaweb.service.system.query.RoleQuery;
import com.javaweb.service.system.service.IMenuService;
import com.javaweb.service.system.service.IRoleService;
import com.javaweb.service.system.vo.role.RoleInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 获取分页列表
     *
     * @param roleQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(RoleQuery roleQuery) {
        return roleService.getList(roleQuery);
    }

    /**
     * 角色详情
     *
     * @param roleId 角色ID
     * @return
     */
    @GetMapping("/info/{roleId}")
    public JsonResult info(@PathVariable("roleId") Integer roleId) {
        Role role = roleService.getById(roleId);
        // 拷贝属性
        RoleInfoVo roleInfoVo = new RoleInfoVo();
        BeanUtils.copyProperties(role, roleInfoVo);

        // 获取角色菜单权限
        List<Menu> menuList = menuService.getMenuListByRoleId(roleId);
        List<Integer> menuIds = new ArrayList<>();
        if (menuList != null) {
            menuList.forEach(item -> {
                menuIds.add(item.getId());
            });
        }
        roleInfoVo.setMenuIds(menuIds.toArray(new Integer[menuIds.size()]));
        return JsonResult.success(roleInfoVo);
    }

    /**
     * 添加角色
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 编辑角色
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Role entity) {
        return roleService.edit(entity);
    }

    /**
     * 删除角色
     *
     * @param roleIds 角色ID
     * @return
     */
    @GetMapping("/delete/{roleIds}")
    public JsonResult delete(@PathVariable("roleIds") String roleIds) {
        return roleService.deleteByIds(roleIds);
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @GetMapping("/getRoleList")
    public JsonResult getRoleList() {
        return roleService.getRoleList();
    }

}
