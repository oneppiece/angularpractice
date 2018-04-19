package com.demo.angularpractice.auth.config;

import com.demo.angularpractice.auth.ajax.AjaxAuthenticationProvider;
import com.demo.angularpractice.auth.ajax.AjaxLoginProcessingFilter;
import com.demo.angularpractice.auth.components.UnauthorizedEntryPoint;
import com.demo.angularpractice.auth.form.HandlerLoginFail;
import com.demo.angularpractice.auth.form.HandlerLoginSuccess;
import com.demo.angularpractice.auth.granted.SecurityAccessDecisionManager;
import com.demo.angularpractice.auth.granted.SecurityInterceptor;
import com.demo.angularpractice.auth.granted.UrlService;
import com.demo.angularpractice.auth.jwt.JwtAuthenticationFilter;
import com.demo.angularpractice.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

/**
 * security 配置
 * Created by Administrator on 15:00.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityConst securityConst;
	//处理表单登陆成功
	@Autowired
	private HandlerLoginFail handlerLoginFail;
	//处理表单登陆失败
	@Autowired
	private HandlerLoginSuccess handlerLoginSuccess;
	//处理ajax登陆失败
	@Autowired
	private AuthenticationFailureHandler ajaxFailureHandler;
	//处理Token认证失败
	@Autowired
	private AuthenticationFailureHandler jwtProcessHandler;
	//处理ajax登陆成功
	@Autowired
	private AuthenticationSuccessHandler ajaxSuccessHandler;
	//授权管理器
	@Autowired
	private AuthenticationManager authenticationManager;
	//ajax认证管理器
	@Autowired
	private AjaxAuthenticationProvider ajaxAuthenticationProvider;
	@Autowired
	private UserMapper userMapper;
	//异常处理
	@Autowired
	private UnauthorizedEntryPoint unauthorizedEntryPoint;

	@Autowired
	private SecurityAccessDecisionManager securityAccessDecisionManager;
	@Autowired
	private UrlService urlService;


	/**
	 * 忽略的静态文件
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**.**");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(ajaxAuthenticationProvider);
	}

	/**
	 * 登陆,注销 的配置
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//跨域
		http.cors()
				.and()
				//iframe显示
				.headers().frameOptions().disable()
				.and()
				// restful下不需要session    (Spring Security will never create an HttpSession)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//异常
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedEntryPoint)
				//表单登陆
				.and()
				.formLogin()
				.loginPage(securityConst.getLogin())
				.loginProcessingUrl(securityConst.getLoginProcess())
				.passwordParameter(securityConst.getLoginPasswordField())
				.usernameParameter(securityConst.getLoginUsernameField())
				.successHandler(handlerLoginSuccess)
				.failureHandler(handlerLoginFail)
				.permitAll()
				//登陆，刷新token，OPTIONS请求任意访问。其他所有资源都需要认证
				.and()
				.authorizeRequests()
				.antMatchers(securityConst.getIgnore()).permitAll()
				.antMatchers(securityConst.getAuthorization()).permitAll()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.anyRequest().authenticated()
				//注销
				.and()
				.logout()
				.logoutUrl(securityConst.getLogout())
				.logoutSuccessUrl(securityConst.getLogin())
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.permitAll()
				//禁用csrf
				.and().csrf().disable()
				//将认证和授权的自定义filter插入制定位置
				.addFilterBefore(buildAjaxLoginProcessingFilter(securityConst.getAuth()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildJWTProcessingFilter(securityConst.getApiroot()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(buildGrantFilter(), JwtAuthenticationFilter.class)
		;


	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 注册处理ajax的登陆认证
	 *
	 * @param pattern 登陆的url
	 * @return
	 */
	protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String pattern) {
		AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(pattern, ajaxSuccessHandler, ajaxFailureHandler);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}

	/**
	 * 注册处理ajax请求的授权
	 *
	 * @param pattern 需要进行权限认证的url(/api/**)
	 * @return
	 */
	protected JwtAuthenticationFilter buildJWTProcessingFilter(String pattern) {
		JwtAuthenticationFilter filter = new JwtAuthenticationFilter(pattern, jwtProcessHandler, userMapper);
		filter.setAuthenticationManager(authenticationManager);
		return filter;
	}

	/**
	 * 注册 授权filter，使用投票器决定是否通过
	 *
	 * @return
	 */
	private Filter buildGrantFilter() {
		SecurityInterceptor securityInterceptor = new SecurityInterceptor();
		securityInterceptor.setAccessDecisionManager(securityAccessDecisionManager);
		securityInterceptor.setAuthenticationManager(authenticationManager);
		securityInterceptor.setSecurityMetadataSource(urlService);
		return securityInterceptor;
	}
}