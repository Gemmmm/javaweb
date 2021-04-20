package com.javaweb.service.generator.service;

import com.javaweb.common.framework.common.IBaseService;
import com.javaweb.service.generator.entity.GenTableColumn;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-17
 */
public interface ITableColumnService extends IBaseService<GenTableColumn> {

    /**
     * 查询表字段信息
     *
     * @param tableId 表ID
     * @return
     */
    List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);

}
