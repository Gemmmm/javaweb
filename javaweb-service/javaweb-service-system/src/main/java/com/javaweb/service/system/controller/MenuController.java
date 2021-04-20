package com.javaweb.service.system.controller;


import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Menu;
import com.javaweb.service.system.query.MenuQuery;
import com.javaweb.service.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

/**
 * <p>
 * 菜单管理表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单列表
     *
     * @param menuQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(MenuQuery menuQuery) {
        return menuService.getList(menuQuery);
    }

    /**
     * 根据菜单ID获取详情
     *
     * @param menuId 菜单ID
     * @return
     */
    @GetMapping("/info/{menuId}")
    public JsonResult info(@PathVariable("menuId") Integer menuId) {
        return menuService.info(menuId);
    }

    /**
     * 添加菜单
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Menu entity) {
        return menuService.edit(entity);
    }

    /**
     * 编辑菜单
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Menu entity) {
        return menuService.edit(entity);
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     * @return
     */
    @GetMapping("/delete/{menuId}")
    public JsonResult delete(@PathVariable("menuId") Integer menuId) {
        return menuService.deleteById(menuId);
    }

    /**
     * 根据上级ID获取子级菜单
     *
     * @param pid 上级ID
     * @return
     */
    @GetMapping("/getListByPid/{pid}")
    public JsonResult getListByPid(@PathVariable("pid") Integer pid) {
        return JsonResult.success(menuService.getListByPid(pid));
    }

    /**
     * 获取菜单路由
     *
     * @return
     */
    @GetMapping("/getMenuRoutesList")
    public JsonResult getMenuRoutesList() {
        return menuService.getMenuRoutesList();
    }

}
