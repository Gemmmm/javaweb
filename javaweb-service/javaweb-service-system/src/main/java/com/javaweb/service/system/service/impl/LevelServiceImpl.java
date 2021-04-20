package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.api.system.entity.Role;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.DateUtils;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.entity.Level;
import com.javaweb.service.system.entity.Position;
import com.javaweb.service.system.mapper.LevelMapper;
import com.javaweb.service.system.query.LevelQuery;
import com.javaweb.service.system.service.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <p>
 * 职级表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-12
 */
@Service
public class LevelServiceImpl extends BaseServiceImpl<LevelMapper, Level> implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;
    @Autowired
    private Validator validator;

    /**
     * 获取职级列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        LevelQuery levelQuery = (LevelQuery) query;
        // 查询条件
        QueryWrapper<Level> queryWrapper = new QueryWrapper<>();
        // 职级名称
        if (!StringUtils.isEmpty(levelQuery.getName())) {
            queryWrapper.like("name", levelQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 分页查询
        IPage<Level> page = new Page<>(levelQuery.getPage(), levelQuery.getSize());
        IPage<Level> pageData = levelMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑职级
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Level entity) {
        // 字段校验
        Set<ConstraintViolation<Level>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Level> item : violationSet) {
            return JsonResult.error(item.getMessage());
        }

        Integer count = 0;
        if (entity.getId() != null && entity.getId() > 0) {
            // 编辑
            count = levelMapper.selectCount(new LambdaQueryWrapper<Level>()
                    .eq(Level::getName, entity.getName())
                    .ne(Level::getId, entity.getId())
                    .eq(Level::getMark, 1));
        } else {
            // 添加
            count = levelMapper.selectCount(new LambdaQueryWrapper<Level>()
                    .eq(Level::getName, entity.getName())
                    .eq(Level::getMark, 1));
        }
        if (count > 0) {
            return JsonResult.error("系统中已存在相同的职级名称");
        }
        return super.edit(entity);
    }
}
