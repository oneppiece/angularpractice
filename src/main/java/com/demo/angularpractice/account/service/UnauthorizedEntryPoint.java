package com.demo.angularpractice.account.service;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return Strings.isNullOrEmpty(header) && "XMLHttpRequest".equalsIgnoreCase(header);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (isAjaxRequest(request)) {
            log.info("UnauthorizedEntryPoint.Ajax");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } else {
            log.info("UnauthorizedEntryPoint.not Ajax");
            response.sendRedirect("/app");
        }
    }
}
