package com.demo.angularpractice.auth.components;

import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.result.ResourceResult;
import com.demo.angularpractice.account.result.RoleResult;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

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
	@CachePut(value = "user", key = "#user.id")
	UserParam registe(UserParam user);

	/**
	 * 禁用用户
	 *
	 * @param user
	 * @return
	 */
	@CacheEvict(value = "user", key = "#user.id")
	UserParam invalidUser(UserParam user);

	/**
	 * 获取用户
	 *
	 * @param id
	 * @return
	 */
	@Cacheable(value = "user", key = "#id")
	UserParam getUserById(Integer id);

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

	/**
	 * 根据URI获取角色
	 *
	 * @param uri
	 * @return
	 */
	Set<String> getRoleByUrl(String uri);
}
