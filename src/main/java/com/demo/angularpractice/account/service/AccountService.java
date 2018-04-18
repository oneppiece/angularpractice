package com.demo.angularpractice.account.service;

import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.result.ResourceResult;
import com.demo.angularpractice.account.result.RoleResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 账户相关接口
 *
 * @author dzy
 */
public interface AccountService extends UserDetailsService {
    /**
     * 修改密码
     *
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    UserParam changePassword(Integer userId, String oldPassword, String newPassword);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    UserParam registe(UserParam user);

    /**
     * 禁用用户
     *
     * @param user
     * @return
     */
    UserParam invalidUser(UserParam user);

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    UserParam login(String userName, String password);

    /**
     * 获取角色列表
     *
     * @param roleParam 角色
     * @return
     */
    List<RoleResult> getRolesByCondition(RoleParam roleParam);

    /**
     * 获取角色列表
     *
     * @param userParam 用户
     * @return
     */
    List<RoleResult> getRolesByUser(UserParam userParam);

    /**
     * 获取资源
     *
     * @param userParam 用户
     * @return
     */
    List<ResourceResult> getResourcesByUser(UserParam userParam);

    /**
     * 获取资源
     *
     * @param roleParam 角色
     * @return
     */
    List<ResourceResult> getResourcesByRole(RoleParam roleParam);

    /**
     * 获取资源
     *
     * @param resourceParam
     * @return
     */
    List<ResourceResult> getResourcesByCondition(ResourceParam resourceParam);
}
