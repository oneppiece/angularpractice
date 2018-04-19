package com.demo.angularpractice.auth.jwt;

import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component("jwtProcessHandler")
public class JWTFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        int value = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(value);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        AjaxResponseEntity<Map<String, Object>> ajaxResponseEntity = new AjaxResponseEntity<>();
        ajaxResponseEntity.setStatus(value);
        ajaxResponseEntity.setReturnTime(new Date());
        ajaxResponseEntity.setSuccess(false);
        ajaxResponseEntity.setMsg("认证失败");
        Map<String, Object> map = Maps.newHashMap();
        map.put("errorCode", value);

        if (exception instanceof BadCredentialsException) {
            map.put("desc", exception.getMessage());
        } else if (exception instanceof UsernameNotFoundException) {
            map.put("desc", exception.getMessage());
        } else if (exception instanceof InsufficientAuthenticationException) {
            map.put("desc", exception.getMessage());
        }
        ajaxResponseEntity.setResponse(map);

        mapper.writeValue(response.getWriter(), ajaxResponseEntity);

    }
}
