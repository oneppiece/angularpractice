package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.account.domain.AppException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dzy
 * <p>
 * Aug 3, 2016
 */
@Component("ajaxFailureHandler")
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {
            mapper.writeValue(response.getWriter(), new AppException(HttpStatus.UNAUTHORIZED.toString(), e.getMessage()));
        } else if (e instanceof AuthMethodNotSupportedException) {
            mapper.writeValue(response.getWriter(), new AppException(HttpStatus.UNAUTHORIZED.toString(), e.getMessage()));
        }
        mapper.writeValue(response.getWriter(), new AppException(HttpStatus.UNAUTHORIZED.toString(), e.getMessage()));
    }
}
