package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.account.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * ajax登陆获取登陆信息,配置成功和失败执行的方法
 * 登陆通过则返回token和refreshToken，客户端每次请求在请求头添加token信息
 *
 * @author dzy
 */
@Slf4j
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter(String defaultProcessUrl, AuthenticationSuccessHandler successHandler,
                                     AuthenticationFailureHandler failureHandler) {
        super(defaultProcessUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    /**
     * 从请求中获取登陆信息
     * 有XMLHttpRequest请求头，则是Ajax请求，传输数据通过getAjax()获取
     * 无XMLHttpRequest请求头，传输数据通过getVal()获取
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        request = new BodyReaderHttpServletRequestWrapper(request);
        boolean ajax = WebUtil.isAjax(request);
        StringBuffer requestJson = new StringBuffer();

        if (!HttpMethod.POST.name().equalsIgnoreCase(request.getMethod())) {
            throw new HttpRequestMethodNotSupportedException(request.getMethod());
        }

        if (ajax) {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                requestJson = requestJson.append(parameterNames.nextElement());
            }
        } else {
            requestJson = requestJson.append(getRequestPayload(request));
        }


        if (StringUtils.isBlank(requestJson)) {
            throw new AuthenticationServiceException("找不到用户名或者密码字段");
        }
        LoginRequest loginRequest = objectMapper.readValue(requestJson.toString(), LoginRequest.class);

        if (StringUtils.isBlank(loginRequest.getUserName()) || StringUtils.isBlank(loginRequest.getPassword())) {
            throw new AuthenticationServiceException("用户名或密码不能为空");
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword());

        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * 用户名密码验证成功后执行 successHandler
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * 验证失败执行 failureHandler
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    /**
     * 通过getReader()读取登陆信息
     * 解决getReader()只能读取一次流异常
     *
     * @param req
     * @return
     */
    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
