package com.demo.angularpractice.account.service.impl;

import com.demo.angularpractice.account.domain.AppException;
import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.result.ResourceResult;
import com.demo.angularpractice.account.result.RoleResult;
import com.demo.angularpractice.account.service.AccountService;
import com.demo.angularpractice.account.util.EntityUtils;
import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.entity.Role;
import com.demo.angularpractice.middle.UserCheckComponent;
import com.demo.angularpractice.repository.UserMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component("userDetailsService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCheckComponent userCheckComponent;
    @Autowired
    private EntityUtils entityUtils;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过数据库来查找到实际的用户信息
        UserParam userInfo = userMapper.selectByUserName(username);
        if (Objects.isNull(userInfo)) {
            throw new UsernameNotFoundException("用户不存在!");
        } else if (!userInfo.isEnabled()) {
            throw new UsernameNotFoundException("用户已被禁用!");
        } else {
            Set<String> strings = userMapper.selectResourcesByUser(userInfo);
            List<Resource> resources = userMapper.selectResourcesByUserId(userInfo.getId());
            List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
            for (Resource resource : resources) {
                if (!Objects.isNull(resource) && !Objects.isNull(resource.getName())) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(resource.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return userInfo;
        }

    }

    @Override
    public UserParam changePassword(Integer userId, String oldPassword, String newPassword) {
        UserParam user = new UserParam();
        user.setId(userId);
        UserParam userParam = userMapper.selectByUser(user);
        if (!Objects.isNull(userParam)) {
            if (userParam.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                userMapper.updateByPrimaryKey(user);
            } else {
                throw new AppException("原密码错误1");
            }
        }
        return userParam;
    }

    @Override
    public UserParam registe(UserParam user) {
        UserParam userParam = userMapper.selectByUser(user);
        if (Objects.isNull(userParam)) {
            userMapper.insertUser(user);
        } else {
            throw new AppException("用户已存在!");
        }
        return user;
    }

    @Override
    public UserParam invalidUser(UserParam user) {
        UserParam userParam = userMapper.selectByUser(user);
        if (!Objects.isNull(userParam)) {
            userParam.setEnabled(Boolean.FALSE);
            userMapper.updateByPrimaryKey(user);
        } else {
            throw new AppException("用户不存在");
        }
        return userParam;
    }

    @Override
    public UserParam login(String userName, String password) {
        return null;
    }

    /**
     * 获取角色列表
     *
     * @param roleParam 角色
     * @return
     */
    @Override
    public List<RoleResult> getRolesByCondition(RoleParam roleParam) {
        //校验参数
        userCheckComponent.checkGetRolesByCondition(roleParam);
        //转换参数
        Role param = EntityUtils.copy(roleParam, Role.class);
        //查询
        List<Role> result = userMapper.selectRolesByCondition(param);
        //转换结果
        List<RoleResult> roleResults = EntityUtils.copyList(result, RoleResult.class);
        return roleResults;
    }

    /**
     * 获取角色列表
     *
     * @param userParam 用户
     * @return
     */
    @Override
    public List<RoleResult> getRolesByUser(UserParam userParam) {
        return null;
    }

    /**
     * 获取资源
     *
     * @param userParam 用户
     * @return
     */
    @Override
    public List<ResourceResult> getResourcesByUser(UserParam userParam) {
        return null;
    }

    /**
     * 获取资源
     *
     * @param roleParam 角色
     * @return
     */
    @Override
    public List<ResourceResult> getResourcesByRole(RoleParam roleParam) {
        return null;
    }

    /**
     * 获取资源
     *
     * @param resourceParam
     * @return
     */
    @Override
    public List<ResourceResult> getResourcesByCondition(ResourceParam resourceParam) {
        return null;
    }
}
