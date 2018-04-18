//package com.demo.angularpractice.account.ajax;
//
//import com.demo.angularpractice.account.param.UserParam;
//import com.demo.angularpractice.account.util.JWTUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.Maps;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * AjaxAwareAuthenticationSuccessHandler
// *
// * @author vladimir.stankovic
// * <p>
// * Aug 3, 2016
// */
//@Component("successHandler")
//public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//	@Autowired
//	private JWTUtil jwtUtil;
//	@Autowired
//	private ObjectMapper mapper;
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//	                                    Authentication authentication) throws IOException, ServletException {
//		UserParam userParam = (UserParam) authentication.getPrincipal();
//		Map<String, Object> map = Maps.newHashMap();
//		map.put("user", userParam);
//		String accessToken = jwtUtil.generateToken(map);
//		response.setStatus(HttpStatus.OK.value());
//		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//		response.getWriter().write(accessToken);
//		clearAuthenticationAttributes(request);
//	}
//
//	/**
//	 * Removes temporary authentication-related data which may have been stored
//	 * in the session during the authentication process..
//	 */
//	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//
//		if (session == null) {
//			return;
//		}
//
//		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//	}
//}
