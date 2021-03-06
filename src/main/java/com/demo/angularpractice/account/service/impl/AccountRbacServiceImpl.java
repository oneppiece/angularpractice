package com.demo.angularpractice.account.service.impl;

import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.service.AccountRbacService;
import com.demo.angularpractice.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Component("rbacService")
@PropertySource("classpath:application.yaml")
public class AccountRbacServiceImpl implements AccountRbacService {
    @Autowired
    private UserMapper userMapper;
    @Value("${server.servlet.context-path}")
    private String appContextName;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String requestURI = request.getRequestURI().substring(appContextName.length());
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            UserParam user = (UserParam) principal;
            Set<ResourceParam> urls = userMapper.selectResourcesByUser(user);
            for (ResourceParam url : urls) {
                if (antPathMatcher.match(url.getUrl(), requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
