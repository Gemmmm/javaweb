package com.javaweb.service.system.query;

import com.javaweb.common.framework.common.BaseQuery;
import lombok.Data;

/**
 * 部门查询条件
 */
@Data
public class DeptQuery extends BaseQuery {

    /**
     * 部门名称
     */
    private String name;

}
