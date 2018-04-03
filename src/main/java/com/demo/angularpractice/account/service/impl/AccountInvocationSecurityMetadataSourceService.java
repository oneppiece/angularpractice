package com.demo.angularpractice.account.service.impl;

import com.demo.angularpractice.entity.Resource;
import com.demo.angularpractice.repository.UserMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author dzy
 */
@Service
public class AccountInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource {

	@Autowired
	private UserMapper userMapper;


	/**
	 * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		HashMap<String, Collection<ConfigAttribute>> map = Maps.newHashMap();
		Collection<ConfigAttribute> array = new ArrayList<>();
		Collection<ConfigAttribute> array404 = new ArrayList<>();
		ConfigAttribute cfg;
		List<Resource> resources = userMapper.selectAllResource();
		for (Resource resource : resources) {
			cfg = new SecurityConfig(resource.getUrl());
			array.add(cfg);
			map.put(resource.getUrl(), array);
		}
		//object 中包含用户请求的request 信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if (matcher.matches(request)) {
				return map.get(resUrl);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
