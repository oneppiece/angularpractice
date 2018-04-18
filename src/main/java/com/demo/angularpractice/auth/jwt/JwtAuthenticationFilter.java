package com.demo.angularpractice.auth.jwt;

import com.demo.angularpractice.account.util.JWTUtil;
import com.demo.angularpractice.auth.config.SecurityConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * liyan-下午9:52
 *
 * @author liyan
 **/
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private final AuthenticationFailureHandler failureHandler;
	@Autowired
	private SecurityConst securityConst;
	@Autowired
	private JWTUtil jwtUtil;

	public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		String tokenFormHeader = request.getHeader(securityConst.tokenHeader);
		SecurityContextHolder.getContext().getAuthentication();
		jwtUtil.validateToken(tokenFormHeader, null);
		return null;
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
