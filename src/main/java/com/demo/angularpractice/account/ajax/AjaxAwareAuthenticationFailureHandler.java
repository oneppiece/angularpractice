//package com.demo.angularpractice.account.ajax;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.Maps;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @author vladimir.stankovic
// * <p>
// * Aug 3, 2016
// */
//@Component("failureHandler")
//@Slf4j
//public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//	                                    AuthenticationException e) throws IOException, ServletException {
//
//		log.info("登录失败");
//		//以Json格式返回
//		Map<String, String> map = Maps.newHashMap();
//		map.put("code", "201");
//		map.put("msg", "登录失败:" + e.getMessage());
//		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//		response.setContentType("application/json");
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(objectMapper.writeValueAsString(map));
//	}
//}
