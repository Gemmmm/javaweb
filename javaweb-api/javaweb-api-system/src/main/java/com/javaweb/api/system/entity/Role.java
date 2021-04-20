package com.javaweb.api.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 系统角色表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    @NotNull(message = "角色名称不能为空")
    @Length(min = 2, max = 30, message = "角色名称长度为2~30个字")
    private String name;

    /**
     * 角色Tag
     */
    @Excel(name = "角色标识")
    @NotNull(message = "角色标识不能为空")
    @Length(min = 2, max = 100, message = "角色标识长度为2~100个字")
    private String tag;

    /**
     * 状态：1正常 2禁用
     */
    @Excel(name = "状态", readConverterExp = "1=正常,2=停用")
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    private String note;

    /**
     * 排序
     */
    @Excel(name = "角色排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
     * 菜单ID数组
     */
    @TableField(exist = false)
    private String[] menuIds;

}
