package com.javaweb.service.system.controller;


import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Dept;
import com.javaweb.service.system.query.DeptQuery;
import com.javaweb.service.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-13
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;

    /**
     * 获取部门列表
     *
     * @param deptQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(DeptQuery deptQuery) {
        return deptService.getList(deptQuery);
    }

    /**
     * 根据部门ID获取详情
     *
     * @param deptId 部门ID
     * @return
     */
    @GetMapping("/info/{deptId}")
    public JsonResult info(@PathVariable("deptId") Integer deptId) {
        return deptService.info(deptId);
    }

    /**
     * 添加部门
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Dept entity) {
        return deptService.add(entity);
    }

    /**
     * 编辑部门
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Dept entity) {
        return deptService.edit(entity);
    }

    /**
     * 根据部门ID删除部门
     *
     * @param deptId 部门ID
     * @return
     */
    @GetMapping("/delete/{deptId}")
    public JsonResult delete(@PathVariable("deptId") Integer deptId) {
        return deptService.deleteById(deptId);
    }

}
