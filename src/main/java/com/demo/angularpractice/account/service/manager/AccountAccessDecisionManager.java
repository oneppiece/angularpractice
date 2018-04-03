package com.demo.angularpractice.account.service.manager;

import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.entity.Role;
import com.demo.angularpractice.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 最终决定的策略。
 *
 * @author dzy
 */
@Service
public class AccountAccessDecisionManager implements AccessDecisionManager {

	@Autowired
	private UserMapper userMapper;
	@Value("${server.servlet.context-path}")
	private String appContextName;

	/**
	 * 如果当前用户所拥有的任意角色包含此次访问的url，则通过。否则，不通过。
	 *
	 * @param authentication
	 * @param object
	 * @param configAttributes
	 * @throws AccessDeniedException
	 * @throws InsufficientAuthenticationException
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (!authentication.isAuthenticated()) {
			throw new AccessDeniedException("no right");
		}
		String requestURI = ((FilterInvocation) object).getRequest().getRequestURI().substring(appContextName.length());

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			String roleName = authority.getAuthority();
			Role role = new Role();
			role.setName(roleName);
			List<Resource> resources = userMapper.selectResourcesByRole(role);
			for (Resource resource : resources) {
				if (resource.getUrl().equals(requestURI)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");

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
