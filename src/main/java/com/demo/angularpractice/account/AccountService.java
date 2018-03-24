package com.demo.angularpractice.account;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 账户相关接口
 */
public interface AccountService extends UserDetailsService {
    SysUserParam changePassword(Integer userId, String oldPassword, String newPassword);

    SysUserParam registe(SysUserParam user);

    SysUserParam invalidUser(SysUserParam user);

    SysUserParam login(String userName, String password);
}
