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
 * 岗位表
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_position")
public class Position extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位名称
     */
    @NotNull(message = "岗位名称不能为空")
    @Length(min = 2, max = 30, message = "岗位名称长度为2~30个字")
    private String name;

    /**
     * 状态：1正常 2停用
     */
    @Excel(name = "状态", readConverterExp = "1=正常,2=停用")
    @NotNull(message = "状态不能为空")
    private Integer status;

    /**
     * 显示顺序
     */
    @Excel(name = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;


}
