package ${packageName}.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageName}.entity.${entityName};
import ${packageName}.mapper.${entityName}Mapper;
import ${packageName}.query.${entityName}Query;
import ${packageName}.service.I${entityName}Service;
import ${packageName}.vo.${entityName?lower_case}.${entityName}InfoVo;
import ${packageName}.vo.${entityName?lower_case}.${entityName}ListVo;
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
  * ${tableAnnotation} 服务类实现
  * </p>
  *
  * @author ${author}
  * @since ${date}
  */
@Service
public class ${entityName}ServiceImpl extends BaseServiceImpl<${entityName}Mapper, ${entityName}> implements I${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${entityName?uncap_first}Mapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        ${entityName}Query ${entityName?uncap_first}Query = (${entityName}Query) query;
        // 查询条件
        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();
<#if model_column?exists>
    <#list model_column as model>
        <#if model.columnName = 'name'>
        // ${model.columnComment!}
        if (!StringUtils.isEmpty(${entityName?uncap_first}Query.getName())) {
            queryWrapper.like("name", ${entityName?uncap_first}Query.getName());
        }
        </#if>
        <#if model.columnName = 'title'>
        // ${model.columnComment!}
        if (!StringUtils.isEmpty(${entityName?uncap_first}Query.getTitle())) {
            queryWrapper.like("title", ${entityName?uncap_first}Query.getTitle());
        }
        </#if>
        <#if model.columnName = 'mobile'>
        // ${model.columnComment!}
        if (!StringUtils.isEmpty(${entityName?uncap_first}Query.getMobile())) {
            queryWrapper.like("mobile", ${entityName?uncap_first}Query.getMobile());
        }
        </#if>
        <#if model.columnName = 'type'>
        // ${model.columnComment!}
        if (!StringUtils.isEmpty(${entityName?uncap_first}Query.getType())) {
            queryWrapper.eq("type", ${entityName?uncap_first}Query.getType());
        }
        </#if>
        <#if model.columnName = 'status'>
        // ${model.columnComment!}
        if (!StringUtils.isEmpty(${entityName?uncap_first}Query.getStatus())) {
            queryWrapper.eq("status", ${entityName?uncap_first}Query.getStatus());
        }
        </#if>
    </#list>
</#if>
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByDesc("id");

        // 获取数据列表
        IPage<${entityName}> page = new Page<>(${entityName?uncap_first}Query.getPage(), ${entityName?uncap_first}Query.getSize());
        IPage<${entityName}> pageData = ${entityName?uncap_first}Mapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            ${entityName}ListVo ${entityName?uncap_first}ListVo = Convert.convert(${entityName}ListVo.class, x);
            return ${entityName?uncap_first}ListVo;
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
        ${entityName} entity = (${entityName}) super.getInfo(id);
        // 返回视图Vo
        ${entityName}InfoVo ${entityName?uncap_first}InfoVo = new ${entityName}InfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, ${entityName?uncap_first}InfoVo);
        return ${entityName?uncap_first}InfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(${entityName} entity) {
        if (entity.getId() != null && entity.getId() > 0) {
    <#if model_column?exists>
        <#list model_column as model>
            <#if model.changeColumnName?uncap_first = 'updateUser'>
            entity.setUpdateUser(1);
            </#if>
            <#if model.changeColumnName?uncap_first = 'updateTime'>
            entity.setUpdateTime(DateUtils.now());
            </#if>
        </#list>
    </#if>
        } else {
    <#if model_column?exists>
        <#list model_column as model>
            <#if model.changeColumnName?uncap_first = 'createUser'>
            entity.setCreateUser(1);
            </#if>
            <#if model.changeColumnName?uncap_first = 'createTime'>
            entity.setCreateTime(DateUtils.now());
            </#if>
        </#list>
    </#if>
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
    public JsonResult delete(${entityName} entity) {
<#if model_column?exists>
    <#list model_column as model>
        <#if model.changeColumnName?uncap_first = 'updateUser'>
        entity.setUpdateUser(1);
        </#if>
        <#if model.changeColumnName?uncap_first = 'updateTime'>
        entity.setUpdateTime(DateUtils.now());
        </#if>
    </#list>
</#if>
        entity.setMark(0);
        return super.delete(entity);
    }
}