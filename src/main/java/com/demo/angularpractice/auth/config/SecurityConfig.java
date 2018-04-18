package com.demo.angularpractice.auth.config;

import com.demo.angularpractice.account.service.UnauthorizedEntryPoint;
import com.demo.angularpractice.account.service.handler.HandlerLoginFail;
import com.demo.angularpractice.account.service.handler.HandlerLoginSuccess;
import com.demo.angularpractice.auth.ajax.AjaxAuthenticationProvider;
import com.demo.angularpractice.auth.ajax.AjaxLoginProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import java.util.Arrays;
import java.util.List;

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
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

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
        List<String> permitAllEndpointList = Arrays.asList(
                securityConst.getAuthorization(),
                securityConst.getToken()
        );
        http.cors()
                .and()
                //Session 超时，最大在线数量
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
        ;


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, ajaxSuccessHandler, ajaxFailureHandler);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

}