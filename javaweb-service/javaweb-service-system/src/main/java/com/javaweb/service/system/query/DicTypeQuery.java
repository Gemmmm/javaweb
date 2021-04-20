package com.javaweb.service.system.query;

import com.javaweb.common.framework.common.BaseQuery;
import lombok.Data;

/**
 * <p>
 * 字典类型表查询条件
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-20
 */
@Data
public class DicTypeQuery extends BaseQuery {

    /**
     * 字典名称
     */
    private String name;

}
