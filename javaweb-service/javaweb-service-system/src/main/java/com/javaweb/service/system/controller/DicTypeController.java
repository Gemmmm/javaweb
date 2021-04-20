package com.javaweb.service.system.controller;

import com.javaweb.common.framework.common.BaseController;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.system.entity.DicType;
import com.javaweb.service.system.query.DicTypeQuery;
import com.javaweb.service.system.service.IDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-20
 */
@RestController
@RequestMapping("/dictype")
public class DicTypeController extends BaseController {

    @Autowired
    private IDicTypeService dicTypeService;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @PostMapping("/index")
    public JsonResult index(@RequestBody DicTypeQuery query) {
        return dicTypeService.getList(query);
    }

    /**
     * 添加记录
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
    }

    /**
     * 获取详情
     *
     * @param dictypeId 记录ID
     * @return
     */
    @GetMapping("/info/{dictypeId}")
    public JsonResult info(@PathVariable("levelId") Integer dictypeId) {
        return dicTypeService.info(dictypeId);
    }

    /**
     * 更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody DicType entity) {
        return dicTypeService.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param dictypeIds 记录ID
     * @return
     */
    @GetMapping("/delete/{dictypeIds}")
    public JsonResult delete(@PathVariable("dictypeIds") String dictypeIds) {
        return dicTypeService.deleteByIds(dictypeIds);
    }
}