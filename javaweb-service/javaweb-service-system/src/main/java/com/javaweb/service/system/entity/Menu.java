package com.javaweb.service.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.javaweb.common.framework.common.BaseEntity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单管理表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    @Length(min = 2, max = 30, message = "菜单名称长度为2~30个字")
    private String name;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 上级ID
     */
    private Integer pid;

    /**
     * 菜单类型：1目录 2菜单 3按钮
     */
    private Integer type;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 是否显示：1显示 2不显示
     */
    private Integer status;

    /**
     * 是否公共：1是 2否
     */
    private Integer isPublic;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 菜单备注
     */
    private String note;

}
