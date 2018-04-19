package com.demo.angularpractice.auth.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    @Getter
    @Setter
    private int code;
    @Setter
    @Getter
    private String msg;

    public AuthException(String msg) {
        super(msg);
    }


}
