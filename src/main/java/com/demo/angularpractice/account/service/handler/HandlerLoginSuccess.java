package com.demo.angularpractice.account.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 登陆成功处理类
 */
@Component
public class HandlerLoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //什么都不做的话，那就直接调用父类的方法
        //super.onAuthenticationSuccess(request, response, authentication);

        //这里可以根据实际情况，来确定是跳转到页面或者json格式。
        //返回json格式
        Map<String, String> map = Maps.newHashMap();
        map.put("code", "200");
        map.put("msg", "登录成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));


        //跳转到某个页面
        // new DefaultRedirectStrategy().sendRedirect(request, response, "/whoami");

    }
}
