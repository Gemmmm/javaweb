package com.javaweb.service.system.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.api.system.entity.Role;
import com.javaweb.api.system.entity.User;
import com.javaweb.api.system.entity.UserInfo;
import com.javaweb.common.framework.common.BaseQuery;
import com.javaweb.common.framework.utils.CommonUtils;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.framework.utils.StringUtils;
import com.javaweb.common.security.common.BaseServiceImpl;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.constants.UserConstant;
import com.javaweb.service.system.dto.UpdatePwdDto;
import com.javaweb.service.system.mapper.RoleMapper;
import com.javaweb.service.system.mapper.UserMapper;
import com.javaweb.service.system.query.UserQuery;
import com.javaweb.service.system.service.IUserRoleService;
import com.javaweb.service.system.service.IUserService;
import com.javaweb.service.system.vo.user.UserListVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-09-18
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 根据用户名获取人员信息
     *
     * @param username 用户名
     * @return
     */
    @Override
    public JsonResult<UserInfo> getInfoByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("mark", 1);
        queryWrapper.last("limit 1");

        User user = userMapper.selectOne(queryWrapper);
        if (StringUtils.isNull(user)) {
            return JsonResult.error("用户名或密码错误");
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(user);

        // 临时菜单权限
        Set<String> permissions = new HashSet<String>();
        permissions.add("*:*:*");
        userInfo.setPermissions(permissions);

        // 临时角色权限
        Set<String> roles = new HashSet<String>();
        roles.add("admin");
        userInfo.setRoles(roles);
        return JsonResult.success(userInfo);
    }

    /**
     * 获取分页列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserQuery userQuery = (UserQuery) query;
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 真实姓名
        if (!StringUtils.isEmpty(userQuery.getRealname())) {
            queryWrapper.like("realname", userQuery.getRealname());
        }
        queryWrapper.eq("mark", 1);
        queryWrapper.orderByAsc("id");

        // 查询分页数据
        IPage<User> page = new Page<>(userQuery.getPage(), userQuery.getSize());
        IPage<User> pageData = userMapper.selectPage(page, queryWrapper);
        pageData.convert(x -> {
            UserListVo userListVo = Convert.convert(UserListVo.class, x);
            // 用户头像
            if (!StringUtils.isEmpty(x.getAvatar())) {
                userListVo.setAvatar(CommonUtils.getImageURL(x.getAvatar()));
            }
            // 性别描述
            if (x.getGender() != null && x.getGender() > 0) {
                userListVo.setGenderName(UserConstant.USER_GENDER_LIST.get(x.getGender()));
            }
            // 获取角色信息
            List<Role> roleList = roleMapper.getRolesByUserId(x.getId());
            if (roleList != null) {
                List<Integer> roleIds = new ArrayList<>();
                List<String> roleNames = new ArrayList<>();
                roleList.forEach(item -> {
                    roleIds.add(item.getId());
                    roleNames.add(item.getName());
                });
                userListVo.setRoleIds(roleIds.toArray(new Integer[roleIds.size()]));
                userListVo.setRoleNames(roleNames.toArray(new String[roleNames.size()]));
            }
            return userListVo;
        });
        return JsonResult.success(pageData);
    }

    /**
     * 获取用户详情
     *
     * @param id 记录ID
     * @return
     */
    @Override
    public Object getInfo(Serializable id) {
        User entity = (User) super.getInfo(id);
        // 拷贝属性
        UserListVo userListVo = new UserListVo();
        BeanUtils.copyProperties(entity, userListVo);

        // 获取角色信息
        List<Role> roleList = roleMapper.getRolesByUserId(entity.getId());
        if (roleList != null) {
            List<Integer> roleIds = new ArrayList<>();
            List<String> roleNames = new ArrayList<>();
            roleList.forEach(item -> {
                roleIds.add(item.getId());
                roleNames.add(item.getName());
            });
            userListVo.setRoleIds(roleIds.toArray(new Integer[roleIds.size()]));
            userListVo.setRoleNames(roleNames.toArray(new String[roleNames.size()]));
        }
        return userListVo;
    }

    /**
     * 添加或编辑
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(User entity) {
        // 真实姓名验证
        if (StringUtils.isEmpty(entity.getRealname())) {
            return JsonResult.error("真实姓名不能为空");
        }
        // 昵称验证
        if (StringUtils.isEmpty(entity.getNickname())) {
            return JsonResult.error("昵称不能为空");
        }
        // 性别
        if (StringUtils.isNull(entity.getGender())) {
            return JsonResult.error("性别不能为空");
        }
        // 用户名验证
        if (StringUtils.isEmpty(entity.getUsername())) {
            return JsonResult.error("登录名不能为空");
        }
        boolean result = false;
        if (entity.getId() != null && entity.getId() > 0) {
            // 编辑
            User user = userMapper.selectById(entity.getId());
            if (user == null) {
                return JsonResult.error("用户信息不存在");
            }
            // 验证修改的登录名是否已存在
            if (!user.getUsername().equals(entity.getUsername())) {
                Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, entity.getUsername())
                        .eq(User::getMark, 1));
                if (count > 0) {
                    return JsonResult.error("当前登录名已存在");
                }
            }
            if (StringUtils.isNotEmpty(entity.getPassword())) {
                entity.setPassword(SecurityUtils.encryptPassword(entity.getPassword()));
            } else {
                entity.setPassword(null);
            }
            result = this.updateById(entity);
        } else {
            // 添加
            // 登录名验证
            Integer count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getUsername, entity.getUsername())
                    .eq(User::getMark, 1));
            if (count > 0) {
                return JsonResult.error("当前登录名已存在");
            }
            if (StringUtils.isEmpty(entity.getPassword())) {
                entity.setPassword("123456");
            }
            entity.setPassword(SecurityUtils.encryptPassword(entity.getPassword()));
            result = this.save(entity);
        }
        if (!result) {
            return JsonResult.error("操作失败");
        }
        // 删除已存在的用户角色关系数据
        userRoleService.deleteUserRole(entity.getId());
        // 插入用户角色关系数据
        userRoleService.insertUserRole(entity.getId(), entity.getRoleIds());
        return super.edit(entity);
    }

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return
     */
    @Override
    public JsonResult resetPwd(Integer userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return JsonResult.error("用户信息不存在");
        }
        user.setPassword(SecurityUtils.encryptPassword("123456"));
        boolean result = updateById(user);
        if (!result) {
            return JsonResult.error("重置密码失败");
        }
        return JsonResult.success("重置密码成功");
    }

    /**
     * 更新密码
     *
     * @param updatePwdDto 参数
     * @return
     */
    @Override
    public JsonResult updatePwd(UpdatePwdDto updatePwdDto) {
        return JsonResult.error("演示系统禁止操作");
//        // 原始密码校验
//        if (StringUtils.isEmpty(updatePwdDto.getOldPassword())) {
//            return JsonResult.error("原始密码不能为空");
//        }
//        // 新密码校验
//        if (StringUtils.isEmpty(updatePwdDto.getPassword())) {
//            return JsonResult.error("密码不能为空");
//        }
//        User user = userMapper.selectById(SecurityUtils.getUserId());
//        if (user == null) {
//            return JsonResult.error("用户信息不存在");
//        }
//        user.setPassword(SecurityUtils.encryptPassword(updatePwdDto.getPassword()));
//        boolean result = updateById(user);
//        if (!result) {
//            return JsonResult.error("修改密码失败");
//        }
//        return JsonResult.success("修改密码成功");
    }
}
