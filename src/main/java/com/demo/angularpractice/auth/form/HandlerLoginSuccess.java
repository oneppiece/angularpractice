package com.demo.angularpractice.auth.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 登陆成功处理类
 *
 * @author dzy
 */
@Component
public class HandlerLoginSuccess extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String token = Jwts.builder()
				.setSubject(authentication.getName())
				//有效期两小时
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
				//采用什么算法是可以自己选择的，不一定非要采用HS512
				.signWith(SignatureAlgorithm.HS512, "dzy".getBytes())
				.compact();

		response.addHeader("token", "Bearer " + token);
		Map<String, String> map = Maps.newHashMap();
		map.put("code", "200");
		map.put("msg", "登录成功");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(map));
	}
}
