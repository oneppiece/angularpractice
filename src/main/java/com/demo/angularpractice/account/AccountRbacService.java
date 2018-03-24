package com.demo.angularpractice.account;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户权限接口
 *
 */
public interface AccountRbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
