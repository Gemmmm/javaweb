package com.javaweb.service.system.query;

import com.javaweb.common.framework.common.BaseQuery;
import lombok.Data;

/**
 * 用户查询条件
 */
@Data
public class UserQuery extends BaseQuery {

    /**
     * 真实姓名
     */
    private String realname;

}
