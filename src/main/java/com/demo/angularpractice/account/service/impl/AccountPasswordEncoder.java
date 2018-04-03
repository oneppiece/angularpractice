package com.demo.angularpractice.account.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 加密解密
 *
 * @author dzy
 */
@Component
public class AccountPasswordEncoder implements PasswordEncoder {

    /**
     * 加密
     *
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    /**
     * 解密
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
}
