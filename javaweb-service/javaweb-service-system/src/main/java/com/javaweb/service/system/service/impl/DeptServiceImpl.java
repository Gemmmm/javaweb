package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.service.system.entity.Dept;
import com.javaweb.service.system.mapper.DeptMapper;
import com.javaweb.service.system.query.DeptQuery;
import com.javaweb.service.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-13
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper depMapper;

    /**
     * 获取部门列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DeptQuery depQuery = (DeptQuery) query;
        // 查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        // 部门名称
        if (!StringUtils.isEmpty(depQuery.getName())) {
            queryWrapper.like("name", depQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        List<Dept> depList = list(queryWrapper);
        return JsonResult.success(depList);
    }

}
