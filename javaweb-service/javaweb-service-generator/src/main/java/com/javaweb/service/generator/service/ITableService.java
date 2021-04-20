package com.javaweb.service.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.javaweb.common.framework.common.IBaseService;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.service.generator.entity.GenTable;
import com.javaweb.service.generator.query.GenTableQuery;

import java.util.List;

/**
 * <p>
 * 代码生成业务表 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-17
 */
public interface ITableService extends IBaseService<GenTable> {

    /**
     * 获取数据库表
     *
     * @param query 查询条件
     * @return
     */
    IPage<GenTable> genDbTableList(GenTableQuery query);

    /**
     * 查询据库列表
     *
     * @param tableNames 表数组
     * @return
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     */
    void importGenTable(List<GenTable> tableList);

    /**
     * 根据表ID获取表信息
     *
     * @param tableId 表ID
     * @return
     */
    GenTable selectGenTableById(Integer tableId);


    /**
     * 业务表保存参数校验
     *
     * @param Table 生成表
     */
    void validateEdit(GenTable Table);

    /**
     * 更新业务表信息
     *
     * @param Table 业务表
     */
    void updateGenTable(GenTable Table);

    /**
     * 生成代码
     *
     * @param tableNames 数据表
     * @return
     */
    JsonResult generatorCode(String[] tableNames);

}
