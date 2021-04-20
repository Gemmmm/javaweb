package com.javaweb.service.system.query;

import com.javaweb.common.framework.common.BaseQuery;
import lombok.Data;

/**
 * 角色查询条件
 */
@Data
public class RoleQuery extends BaseQuery {

    /**
     * 角色名称
     */
    private String name;

}
