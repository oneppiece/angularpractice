package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.auth.components.AccountPasswordEncoder;
import com.demo.angularpractice.auth.components.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 针对非Form请求的Provider
 *
 * @author liyan
 */
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AccountPasswordEncoder encoder;
    @Autowired
    private AccountService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "找不到授权信息!");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户: " + username + "不存在!");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不匹配!");
        }

        if (user.getAuthorities() == null)
            throw new InsufficientAuthenticationException("用户没有任何权限!");

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
