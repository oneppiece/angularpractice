//package com.demo.angularpractice.account.filter;
//
//import com.demo.angularpractice.account.service.handler.HandlerLoginSuccess;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.util.Assert;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * liyan-下午8:16
// *
// * @author liyan
// **/
//public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
//
//	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "userName";
//	// ~ Static fields/initializers
//	// =====================================================================================
//	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
//	@Autowired
//	private ObjectMapper mapper;
//	private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
//	private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
//	private boolean postOnly = true;
//
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private HandlerLoginSuccess handlerLoginSuccess;
//
//	public JWTLoginFilter(AuthenticationManager authenticationManager) {
//		super(new AntPathRequestMatcher("/doLogin", "POST"));
//		this.authenticationManager = authenticationManager;
//	}
//
//	// ~ Constructors
//	// ===================================================================================================
//
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//		chain.doFilter(request, response);
//	}
//
//	// ~ Methods
//	// ========================================================================================================
//
//	public Authentication attemptAuthentication(HttpServletRequest request,
//	                                            HttpServletResponse response) throws AuthenticationException {
//		if (postOnly && !request.getMethod().equals("POST")) {
//			throw new AuthenticationServiceException(
//					"Authentication method not supported: " + request.getMethod());
//		}
//
//		String username = obtainUsername(request);
//		String password = obtainPassword(request);
//
//		if (username == null) {
//			username = "";
//		}
//
//		if (password == null) {
//			password = "";
//		}
//
//		username = username.trim();
//
//		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
//				username, password);
//
//		setDetails(request, authRequest);
//
//		return authRequest;
//	}
//
//	protected String obtainPassword(HttpServletRequest request) {
//		return request.getParameter(passwordParameter);
//	}
//
//	protected String obtainUsername(HttpServletRequest request) {
//		return request.getParameter(usernameParameter);
//	}
//
//	protected void setDetails(HttpServletRequest request,
//	                          UsernamePasswordAuthenticationToken authRequest) {
//		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//	}
//
//	public void setPostOnly(boolean postOnly) {
//		this.postOnly = postOnly;
//	}
//
//	public final String getUsernameParameter() {
//		return usernameParameter;
//	}
//
//	public void setUsernameParameter(String usernameParameter) {
//		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
//		this.usernameParameter = usernameParameter;
//	}
//
//	public final String getPasswordParameter() {
//		return passwordParameter;
//	}
//
//	public void setPasswordParameter(String passwordParameter) {
//		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
//		this.passwordParameter = passwordParameter;
//	}
//}
