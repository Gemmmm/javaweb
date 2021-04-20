package com.javaweb.service.system.controller;


import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.Level;
import com.javaweb.service.system.query.LevelQuery;
import com.javaweb.service.system.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

/**
 * <p>
 * 职级表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-12
 */
@RestController
@RequestMapping("/level")
public class LevelController extends BaseController {

    @Autowired
    private ILevelService levelService;

    /**
     * 获取职级列表
     *
     * @param levelQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(LevelQuery levelQuery) {
        return levelService.getList(levelQuery);
    }

    /**
     * 根据职级ID获取职级信息
     *
     * @param levelId 职级ID
     * @return
     */
    @GetMapping("/info/{levelId}")
    public JsonResult info(@PathVariable("levelId") Integer levelId) {
        return levelService.info(levelId);
    }

    /**
     * 添加职级
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 编辑职级
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody Level entity) {
        return levelService.edit(entity);
    }

    /**
     * 删除职级
     *
     * @param levelIds 职级ID
     * @return
     */
    @GetMapping("/delete/{levelIds}")
    public JsonResult delete(@PathVariable("levelIds") String levelIds) {
        return levelService.deleteByIds(levelIds);
    }

}
