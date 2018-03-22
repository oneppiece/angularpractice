package com.demo.angularpractice.service;

import com.demo.angularpractice.entity.User;

/**
 * liyan-下午9:57
 **/
public interface AccountService {
    User login(String userName, String password);

    User changePassword(String userId, String oldPassword, String newPassword);

    User registe(User user);
}
