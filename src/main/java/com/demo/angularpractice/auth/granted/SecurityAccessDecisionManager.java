package com.demo.angularpractice.auth.granted;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 投票器
 * 获取UrlService中存储的当前uri需要的权限
 * liyan-下午8:56
 *
 * @author liyan
 **/
@Component
public class SecurityAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 策略：只能访问角色内的URI，任何数据库中不存在的URI和不匹配的角色 都返回401
	 *
	 * @param authentication   用户权限信息
	 * @param object           url
	 * @param configAttributes UrlService中存储的角色
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException
	 */
	@Override
	public void decide(Authentication authentication, Object object,
	                   Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			throw new AccessDeniedException("无权限");
//			return;
		}

		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		//判断用户所拥有的权限，是否符合对应的Url权限，如果实现了UserDetailsService，则用户权限是loadUserByUsername返回用户所对应的权限
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = (ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException("无权限");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}