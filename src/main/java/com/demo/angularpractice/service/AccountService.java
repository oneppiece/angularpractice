package com.demo.angularpractice.service;

import com.demo.angularpractice.account.param.UserParam;

/**
 * liyan-下午9:57
 **/
public interface AccountService {

    UserParam changePassword(String userId, String oldPassword, String newPassword);

    UserParam login(String userName, String password);

}
