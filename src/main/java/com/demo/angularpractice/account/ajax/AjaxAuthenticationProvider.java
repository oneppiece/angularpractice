//package com.demo.angularpractice.account.ajax;
//
//import com.demo.angularpractice.account.param.UserParam;
//import com.demo.angularpractice.account.service.impl.AccountPasswordEncoder;
//import com.demo.angularpractice.repository.UserMapper;
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import java.util.Objects;
//
///**
// * @author vladimir.stankovic
// * <p>
// * Aug 3, 2016
// */
//@Component
//public class AjaxAuthenticationProvider implements AuthenticationProvider {
//	@Autowired
//	private AccountPasswordEncoder encoder;
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		Assert.notNull(authentication, "No authentication data provided");
//
//		String username = (String) authentication.getPrincipal();
//		String password = (String) authentication.getCredentials();
//
//		UserParam user = userMapper.selectByUserName(username);
//		if (Objects.isNull(user)) {
//			throw new UsernameNotFoundException("User not found: " + username);
//		}
//
//		if (!encoder.matches(password, user.getPassword())) {
//			throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
//		}
//
//		if (user.getAuthorities() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
//
//
//		return new UsernamePasswordAuthenticationToken(username, password, Lists.newArrayList());
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return true;
//	}
//}
