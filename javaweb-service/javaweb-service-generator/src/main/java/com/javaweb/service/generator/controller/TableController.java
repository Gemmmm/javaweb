package com.javaweb.service.generator.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.generator.entity.GenTable;
import com.javaweb.service.generator.entity.GenTableColumn;
import com.javaweb.service.generator.query.GenTableQuery;
import com.javaweb.service.generator.service.ITableColumnService;
import com.javaweb.service.generator.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成业务表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-17
 */
@RestController
@RequestMapping("/gentable")
public class TableController extends BaseController {

    @Autowired
    private ITableService tableService;

    @Autowired
    private ITableColumnService tableColumnService;

    /**
     * 获取业务表列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(GenTableQuery query) {
        return tableService.getList(query);
    }

    /**
     * 获取数据库表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping("/genDbTableList")
    public JsonResult genDbTableList(GenTableQuery query) {
        IPage<GenTable> pageData = tableService.genDbTableList(query);
        return JsonResult.success(pageData, "操作成功");
    }

    /**
     * 导入表
     *
     * @param tables 数据表
     * @return
     */
    @PostMapping("/importTable")
    public JsonResult importTable(@RequestBody String tables) {
        String[] tableNames = tables.split(",");
        // 查询表信息
        List<GenTable> tableList = tableService.selectDbTableListByNames(tableNames);
        tableService.importGenTable(tableList);
        return JsonResult.success();
    }

    /**
     * 获取表详情信息
     *
     * @param tableId 表ID
     * @return
     */
    @GetMapping("/getTableInfo/{tableId}")
    public JsonResult getTableInfo(@PathVariable("tableId") String tableId) {
        GenTable table = tableService.selectGenTableById(Integer.valueOf(tableId));
        List<GenTableColumn> list = tableColumnService.selectGenTableColumnListByTableId(Integer.valueOf(tableId));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("records", list);
        return JsonResult.success(map, "操作成功");
    }

    /**
     * 更新代码生成表信息
     *
     * @param genTable 生成表
     * @return
     */
    @PostMapping("/updateGenTable")
    public JsonResult updateGenTable(@Validated @RequestBody GenTable genTable) {
        return JsonResult.error("演示环境禁止操作");
//        tableService.validateEdit(genTable);
//        tableService.updateGenTable(genTable);
//        return JsonResult.success();
    }

    /**
     * 删除业务表
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody GenTable entity) {
        return tableService.delete(entity);
    }

    /**
     * 生成代码
     *
     * @param tables
     * @throws Exception
     */
    @PostMapping("/batchGenCode")
    public JsonResult batchGenCode(@RequestBody String tables) throws IOException {
        return JsonResult.error("演示环境禁止操作");
//        String[] tableNames = tables.split(",");
//        return tableService.generatorCode(tableNames);
    }

}
