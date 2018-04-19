package com.demo.angularpractice.auth.config;

import com.demo.angularpractice.account.service.handler.HandlerLoginFail;
import com.demo.angularpractice.account.service.handler.HandlerLoginSuccess;
import com.demo.angularpractice.auth.ajax.AjaxAuthenticationProvider;
import com.demo.angularpractice.auth.ajax.AjaxLoginProcessingFilter;
import com.demo.angularpractice.auth.components.UnauthorizedEntryPoint;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * security 配置
 * Created by Administrator on 15:00.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityConst securityConst;
    @Autowired
    private HandlerLoginSuccess handlerLoginSuccess;
    @Autowired
    private HandlerLoginFail handlerLoginFail;
    @Autowired
    private AuthenticationSuccessHandler ajaxSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler ajaxFailureHandler;
    @Autowired
    private AuthenticationFailureHandler jwtProcessHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;


    /**
     * 忽略的静态文件
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**.**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }

    /**
     * 登陆,注销 的配置
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                //Session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //异常
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                //表单
                .and()
                .formLogin()
                .loginPage(securityConst.getLogin())
                .loginProcessingUrl(securityConst.getLoginProcess())
                .passwordParameter(securityConst.getLoginPasswordField())
                .usernameParameter(securityConst.getLoginUsernameField())
                .successHandler(handlerLoginSuccess)
                .failureHandler(handlerLoginFail)
                .permitAll()
                //其他所有资源都需要认证，首页任意访问
                .and()
                .authorizeRequests()
                .antMatchers(securityConst.getIgnore()).permitAll()
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
                .and().csrf().disable()
                .addFilterBefore(buildAjaxLoginProcessingFilter(securityConst.getAuth()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJWTProcessingFilter(securityConst.getApiroot()), UsernamePasswordAuthenticationFilter.class)
        ;


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, ajaxSuccessHandler, ajaxFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    protected JwtAuthenticationFilter buildJWTProcessingFilter(String pattern) {
        AntPathRequestMatcher matchers = new AntPathRequestMatcher(pattern, HttpMethod.GET.name());
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter("/api/**", jwtProcessHandler, userMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

}