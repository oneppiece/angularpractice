package com.demo.angularpractice.auth.granted;

import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.result.RoleResult;
import com.demo.angularpractice.auth.components.AccountService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 通过url获取角色
 * 并存入 Collection<ConfigAttribute> 供SecurityAccessDecisionManager使用
 * liyan-下午8:55
 *
 * @author liyan
 **/
@Service
public class UrlService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private AccountService accountService;
	@Value("${server.servlet.context-path}")
	private String appContext;

	/**
	 * @param object 受保护的对象
	 * @return
	 * @throws IllegalArgumentException
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// 获取当前的URL地址
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String uri = filterInvocation.getRequest().getRequestURI().substring(appContext.length());
		Set<String> roles = accountService.getRoleByUrl(uri);
		if (!Objects.isNull(roles)) {
			Set<SecurityConfig> securityConfigs = Sets.newHashSet();
			for (String role : roles) {
				SecurityConfig securityConfig = new SecurityConfig(role);
				securityConfigs.add(securityConfig);
			}
			Collection<ConfigAttribute> c = Sets.newHashSet();
			c.addAll(securityConfigs);
			return c;
		} else {
			// 如果返回为null则说明此url地址不需要相应的角色就可以访问, 这样Security会放行
			return null;
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> configAttributes = Sets.newHashSet();
		List<RoleResult> rolesByCondition = accountService.getRolesByCondition(new RoleParam());
		for (RoleResult roleResult : rolesByCondition) {
			configAttributes.add(new SecurityConfig(roleResult.getName()));
		}
		return configAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}


}