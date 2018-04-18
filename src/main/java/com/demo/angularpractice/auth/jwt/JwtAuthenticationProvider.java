package com.demo.angularpractice.auth.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * 验证Token
 *
 * @author liyan
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.notNull(authentication, "找不到授权信息!");
		UserDetails user = (UserDetails) authentication.getPrincipal();

		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}


	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}
}
