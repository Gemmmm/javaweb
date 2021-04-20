package com.javaweb.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.common.framework.annotation.Excel;
import com.javaweb.common.framework.common.BaseEntity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @NotNull(message = "部门名称不能为空")
    @Length(min = 2, max = 30, message = "部门名称长度为2~30个字")
    private String name;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 类型：1公司 2部门
     */
    private Integer type;

    /**
     * 是否有子级：1有 2无
     */
    private Integer hasChild;

    /**
     * 排序
     */
    @Excel(name = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
