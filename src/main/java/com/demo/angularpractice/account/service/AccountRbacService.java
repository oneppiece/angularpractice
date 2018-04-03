package com.demo.angularpractice.account.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户权限接口 Rbac
 *
 * @author dzy
 */
public interface AccountRbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
