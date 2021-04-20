package com.javaweb.service.system.query;

import com.javaweb.common.framework.common.BaseQuery;
import lombok.Data;

/**
 * 菜单查询条件
 */
@Data
public class MenuQuery extends BaseQuery {

    /**
     * 菜单名称
     */
    private String name;

}
