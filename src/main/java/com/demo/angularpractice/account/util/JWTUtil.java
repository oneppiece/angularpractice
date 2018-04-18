package com.demo.angularpractice.account.util;

import com.demo.angularpractice.account.param.UserParam;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * liyan-下午10:15
 *
 * @author liyan
 **/
@Component
public class JWTUtil {

	/**
	 * 密钥
	 */
	private final String secret = "mySecret";

	/**
	 * 从数据声明生成令牌
	 *
	 * @param claims 数据声明
	 * @return 令牌
	 */
	private String generateToken(Map<String, Object> claims) {
		Date expirationDate = new Date(System.currentTimeMillis() + 604800L * 1000);
		return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * 从令牌中获取数据声明
	 *
	 * @param token 令牌
	 * @return 数据声明
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	/**
	 * 生成令牌
	 *
	 * @param userDetails 用户
	 * @return 令牌
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>(2);
		claims.put("sub", userDetails.getUsername());
		claims.put("url", userDetails.getAuthorities());
		claims.put("created", new Date());
		return generateToken(claims);
	}

	/**
	 * 从令牌中获取用户名
	 *
	 * @param token 令牌
	 * @return 用户名
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 判断令牌是否过期
	 *
	 * @param token 令牌
	 * @return 是否过期
	 */
	public Boolean isTokenExpired(String token) {
		try {
			Claims claims = getClaimsFromToken(token);
			Date expiration = claims.getExpiration();
			return expiration.before(new Date());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 刷新令牌
	 *
	 * @param token 原令牌
	 * @return 新令牌
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put("created", new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * 验证令牌
	 *
	 * @param token       令牌
	 * @param userDetails 用户
	 * @return 是否有效
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		UserParam user = (UserParam) userDetails;
		String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}

	@Test
	public void testGet() {
		UserParam userDetails = new UserParam();
		List<GrantedAuthority> urls = Lists.newArrayList(new SimpleGrantedAuthority("admin"));
		userDetails.setUsername("dzy");
		userDetails.setPassword("aaa");
		userDetails.setRoleName("admin");
		userDetails.setAuthorities(urls);

		String token = this.generateToken(userDetails);
		String refreshToken = this.refreshToken(token);

		Claims claimsFromToken = this.getClaimsFromToken(token);
		boolean validateToken = this.validateToken(token, userDetails);
		String usernameFromToken = this.getUsernameFromToken(token);
		boolean validateRefreshToken = this.validateToken(refreshToken, userDetails);

		System.out.println(token);
		System.out.println(claimsFromToken.toString());
		System.out.println(usernameFromToken);
		System.out.println(validateToken);
		System.out.println(validateRefreshToken);
	}
}
