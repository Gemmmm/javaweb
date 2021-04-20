package com.javaweb.service.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Position;
import com.javaweb.service.system.query.PositionQuery;
import com.javaweb.service.system.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/position")
public class PositionController extends BaseController {

    @Autowired
    private IPositionService positionService;

    /**
     * 获取岗位列表
     *
     * @param positionQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(PositionQuery positionQuery) {
        return positionService.getList(positionQuery);
    }

    /**
     * 根据岗位ID获取详情
     *
     * @param positionId 岗位ID
     * @return
     */
    @GetMapping("/info/{positionId}")
    public JsonResult info(@PathVariable("positionId") Integer positionId) {
        return positionService.info(positionId);
    }

    /**
     * 添加岗位
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 编辑岗位
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Position entity) {
        return positionService.edit(entity);
    }

    /**
     * 删除岗位
     *
     * @param positionIds 岗位ID
     * @return
     */
    @GetMapping("/delete/{positionIds}")
    public JsonResult delete(@PathVariable("positionIds") String positionIds) {
        return positionService.deleteByIds(positionIds);
    }
}
