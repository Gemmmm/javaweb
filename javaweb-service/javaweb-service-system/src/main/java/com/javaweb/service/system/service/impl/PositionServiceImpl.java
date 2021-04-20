package com.javaweb.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.DateUtils;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.entity.Level;
import com.javaweb.service.system.entity.Position;
import com.javaweb.service.system.mapper.PositionMapper;
import com.javaweb.service.system.query.PositionQuery;
import com.javaweb.service.system.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-12
 */
@Service
public class PositionServiceImpl extends BaseServiceImpl<PositionMapper, Position> implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private Validator validator;

    /**
     * 获取岗位列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        PositionQuery positionQuery = (PositionQuery) query;
        // 查询条件
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        // 岗位名称
        if (!StringUtils.isEmpty(positionQuery.getName())) {
            queryWrapper.like("name", positionQuery.getName());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("sort");

        // 查询数据
        IPage<Position> page = new Page<>(positionQuery.getPage(), positionQuery.getSize());
        IPage<Position> pageData = positionMapper.selectPage(page, queryWrapper);
        return JsonResult.success(pageData);
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Position entity) {
        // 字段校验
        Set<ConstraintViolation<Position>> violationSet = validator.validate(entity);
        for (ConstraintViolation<Position> item : violationSet) {
            return JsonResult.error(item.getMessage());
        }
        Integer count = 0;
        if (entity.getId() != null && entity.getId() > 0) {
            // 编辑
            count = positionMapper.selectCount(new LambdaQueryWrapper<Position>()
                    .eq(Position::getName, entity.getName())
                    .ne(Position::getId, entity.getId())
                    .eq(Position::getMark, 1));
        } else {
            // 添加
            count = positionMapper.selectCount(new LambdaQueryWrapper<Position>()
                    .eq(Position::getName, entity.getName())
                    .eq(Position::getMark, 1));
        }
        if (count > 0) {
            return JsonResult.error("系统中已存在相同的岗位名称");
        }
        return super.edit(entity);
    }
}
