package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author dzy
 * <p>
 * Aug 3, 2016
 */
@Component("ajaxFailureHandler")
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        AjaxResponseEntity<String> result = new AjaxResponseEntity<>();
        result.setMsg("登陆失败！");
        result.setSuccess(Boolean.FALSE);
        result.setReturnTime(new Date());
        result.setStatus(HttpStatus.UNAUTHORIZED.value());
        result.setResponse(e.getMessage());

        mapper.writeValue(response.getWriter(), result);
    }
}
