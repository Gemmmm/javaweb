package com.javaweb.service.generator.service.impl;

import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.service.generator.entity.GenTableColumn;
import com.javaweb.service.generator.mapper.TableColumnMapper;
import com.javaweb.service.generator.service.ITableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成业务表字段 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-17
 */
@Service
public class TableColumnServiceImpl extends BaseServiceImpl<TableColumnMapper, GenTableColumn> implements ITableColumnService {

    @Autowired
    private TableColumnMapper genTableColumnMapper;

    /**
     * 获取表字段信息
     *
     * @param tableId 表ID
     * @return
     */
    @Override
    public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId) {
        return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
    }

}
