package com.demo.angularpractice.account;

import com.demo.angularpractice.account.filter.JWTAuthenticationFilter;
import com.demo.angularpractice.account.filter.JWTLoginFilter;
import com.demo.angularpractice.account.filter.SecurityInterceptorFilter;
import com.demo.angularpractice.account.service.impl.AccountAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 * security 配置
 * Created by Administrator on 15:00.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private AccountAuthenticationProvider accountAuthenticationProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(WebSecurity web) throws Exception {
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(accountAuthenticationProvider);
	}

	/**
	 * 登陆,注销 的配置
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and().authorizeRequests()
				//其他所有资源都需要认证，首页任意访问
				.antMatchers("/", "/login", "/static/**").permitAll()
				.anyRequest().authenticated()
				.and()
				//Session 超时，最大在线数量
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				//注销
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				.and().csrf().disable()
				.addFilterAt(new JWTLoginFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new SecurityInterceptorFilter(), FilterSecurityInterceptor.class)
				.addFilterAfter(new JWTAuthenticationFilter(authenticationManager), JWTLoginFilter.class)
		;
	}

}