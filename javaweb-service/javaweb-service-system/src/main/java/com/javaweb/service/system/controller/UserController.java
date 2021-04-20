package com.javaweb.service.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaweb.api.system.entity.User;
import com.javaweb.api.system.entity.UserInfo;
import com.javaweb.common.framework.utils.JsonResult;
import com.javaweb.common.security.utils.SecurityUtils;
import com.javaweb.service.system.dto.UpdatePwdDto;
import com.javaweb.service.system.query.UserQuery;
import com.javaweb.service.system.service.IUserRoleService;
import com.javaweb.service.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.common.framework.common.BaseController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-09-18
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 获取用户列表
     *
     * @param userQuery 查询条件
     * @return
     */
    @GetMapping("/index")
    public JsonResult index(UserQuery userQuery) {
        return userService.getList(userQuery);
    }

    /**
     * 获取用户信息详情
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/detail/{userId}")
    public JsonResult detail(@PathVariable("userId") Integer userId) {
        return userService.info(userId);
    }

    /**
     * 添加用户
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/add")
    public JsonResult add(@RequestBody User entity) {
        return userService.add(entity);
    }

    /**
     * 编辑用户
     *
     * @param entity 实体对象
     * @return
     */
    @PostMapping("/edit")
    public JsonResult edit(@RequestBody User entity) {
        return userService.edit(entity);
    }

    /**
     * 删除用户
     *
     * @param userIds 用户ID集合
     * @return
     */
    @GetMapping("/delete/{userIds}")
    public JsonResult delete(@PathVariable("userIds") String userIds) {
        return userService.deleteByIds(userIds);
    }

    /**
     * 重置密码
     *
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/resetPwd/{userId}")
    public JsonResult resetPwd(@PathVariable("userId") Integer userId) {
        return userService.resetPwd(userId);
    }

    /**
     * 更新密码
     *
     * @param updatePwdDto 参数
     * @return
     */
    @PostMapping("/updatePwd")
    public JsonResult updatePwd(@RequestBody UpdatePwdDto updatePwdDto) {
        return userService.updatePwd(updatePwdDto);
    }

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/info/{username}")
    public JsonResult<UserInfo> info(@PathVariable("username") String username) {
        return userService.getInfoByName(username);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/getInfo")
    public JsonResult getInfo() {
        Map<String, Object> map = new HashMap<>(3);
        // 获取登录用户ID
        Integer userId = SecurityUtils.getLoginUser().getUserId();
        // 根据用户ID获取用户信息
        User user = userService.getById(userId);
        map.put("user", user);
        // 获取用户角色
        Set<String> roles = userRoleService.getRolesByUserId(userId);
        map.put("roles", roles);
        // 获取用户权限
        Set<String> permissions = userRoleService.getPermissionsByUserId(userId);
        map.put("permissions", permissions);
        return JsonResult.success(map);
    }

}
