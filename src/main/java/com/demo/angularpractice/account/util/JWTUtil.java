package com.demo.angularpractice.account.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * liyan-下午10:15
 *
 * @author liyan
 **/
@Component
public class JWTUtil {

	private final String secret = "dzy";
	@Autowired
	private DateTimeUtil dateTimeUtil;

	public String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret) //采用什么算法是可以自己选择的，不一定非要采用HS512
				.compact();
	}

	public Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public Date generateExpirationDate() {
		return dateTimeUtil.plusHours(System.currentTimeMillis(), 2).toDate();
	}
}
