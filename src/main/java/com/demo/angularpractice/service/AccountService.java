package com.demo.angularpractice.service;

import com.demo.angularpractice.account.SysUserParam;

/**
 * liyan-下午9:57
 **/
public interface AccountService {

    SysUserParam changePassword(String userId, String oldPassword, String newPassword);

    SysUserParam login(String userName, String password);

}
