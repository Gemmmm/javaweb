package com.javaweb.service.system.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.service.system.entity.DicType;
import com.javaweb.service.system.mapper.DicTypeMapper;
import com.javaweb.service.system.query.DicTypeQuery;
import com.javaweb.service.system.service.IDicTypeService;
import com.javaweb.service.system.vo.dictype.DicTypeInfoVo;
import com.javaweb.service.system.vo.dictype.DicTypeListVo;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.framework.utils.DateUtils;
import com.javaweb.common.framework.utils.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
  * <p>
  * 字典类型表 服务类实现
  * </p>
  *
  * @author 鲲鹏
  * @since 2020-10-20
  */
@Service
public class DicTypeServiceImpl extends BaseServiceImpl<DicTypeMapper, DicType> implements IDicTypeService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DicTypeQuery dicTypeQuery = (DicTypeQuery) query;
        // 查询条件
        QueryWrapper<DicType> queryWrapper = new QueryWrapper<>();
        // 字典名称
        if (!StringUtils.isEmpty(dicTypeQuery.getName())) {
            queryWrapper.like("name", dicTypeQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<DicType> page = new Page<>(dicTypeQuery.getPage(), dicTypeQuery.getSize());
        IPage<DicType> pageData = dicTypeMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            DicTypeListVo dicTypeListVo = Convert.convert(DicTypeListVo.class, x);
            return dicTypeListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取详情Vo
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        DicType entity = (DicType) super.getInfo(id);
        // 返回视图Vo
        DicTypeInfoVo dicTypeInfoVo = new DicTypeInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, dicTypeInfoVo);
        return dicTypeInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(DicType entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            entity.setUpdateUser(1);
            entity.setUpdateTime(DateUtils.now());
        } else {
            entity.setCreateUser(1);
            entity.setCreateTime(DateUtils.now());
        }
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(DicType entity) {
        entity.setUpdateUser(1);
        entity.setUpdateTime(DateUtils.now());
        entity.setMark(0);
        return super.delete(entity);
    }
}