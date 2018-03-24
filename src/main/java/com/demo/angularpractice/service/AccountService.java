package com.demo.angularpractice.service;

import com.demo.angularpractice.account.SysUserParam;
import com.demo.angularpractice.entity.User;

/**
 * liyan-下午9:57
 **/
public interface AccountService {

    SysUserParam changePassword(String userId, String oldPassword, String newPassword);

    SysUserParam registe(User user);

    SysUserParam invalidUser(User user);

    SysUserParam login(String userName, String password);

}
