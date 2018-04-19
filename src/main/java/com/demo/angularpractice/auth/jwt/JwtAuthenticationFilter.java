package com.demo.angularpractice.auth.jwt;

import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.util.JWTUtil;
import com.demo.angularpractice.repository.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * liyan-下午9:52
 *
 * @author liyan
 **/
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationFailureHandler failureHandler;
    private JWTUtil jwtUtil;
    private ObjectMapper objectMapper;
    private UserMapper userMapper;

    public JwtAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationFailureHandler failureHandler, UserMapper userMapper) {
        super(defaultFilterProcessesUrl);
        this.failureHandler = failureHandler;
        this.objectMapper = new ObjectMapper();
        this.jwtUtil = new JWTUtil();
        this.userMapper = userMapper;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI().substring("/app".length());

        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationServiceException("未授权访问");
        } else {
            Claims claims = jwtUtil.getClaimsFromToken(token);
            boolean isValidate = jwtUtil.validateToken(token);
            boolean isAuth = validateAuthentication(claims, requestURI);
            if (!isValidate) {
                throw new CredentialsExpiredException("token不可用");
            }
            if (!isAuth) {
                throw new AuthenticationServiceException("无权访问");
            }
            return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Lists.newArrayList());
        }

    }

    private boolean validateAuthentication(Claims token, String requestURI) {
        String username = token.getSubject();
        UserParam userParam = new UserParam();
        userParam.setUsername(username);
        Set<ResourceParam> resources = userMapper.selectResourcesByUser(userParam);
        ArrayList<ResourceParam> urls = Lists.newArrayList(resources);
        List<RequestMatcher> requestMatchers = Lists.newArrayList();
        for (ResourceParam url : urls) {
            if (url.getUrl().equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
