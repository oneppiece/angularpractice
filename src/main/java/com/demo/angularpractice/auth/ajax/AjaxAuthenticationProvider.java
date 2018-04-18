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

@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private AccountPasswordEncoder encoder;
    @Autowired
    private AccountService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);
        if (Objects.isNull(user)) {
            new UsernameNotFoundException("User not found: " + username);
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        if (user.getAuthorities() == null)
            throw new InsufficientAuthenticationException("User has no authorities assigned");

        return new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
