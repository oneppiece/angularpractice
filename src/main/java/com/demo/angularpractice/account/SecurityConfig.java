package com.demo.angularpractice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

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
    private UserDetailsService userDetailsService;

    @Autowired
    private AccountPasswordEncoder accountPasswordEncoder;

    @Autowired
    private HandlerLoginSuccess handlerLoginSuccess;
    @Autowired
    private HandlerLoginFail handlerLoginFail;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;


    /**
     * 忽略的静态文件
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**.**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(accountAuthenticationProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(accountPasswordEncoder)
        ;
    }

    /**
     * 登陆,注销 的配置
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //其他所有资源都需要认证，首页任意访问
                .antMatchers("/", "/login", "/static/**/**.**", "/static/**.**", "/static/**/**/**.**").permitAll()
                .anyRequest().authenticated()
                .and()
                //登录入口
                .formLogin()
                //登录页面入口
                .loginPage("/login")
                //登录表单中字段
                .loginProcessingUrl("/doLogin")
                .passwordParameter("password")
                .usernameParameter("username")
                //处理登陆成功
                .successHandler(handlerLoginSuccess)
                //处理登陆成功
                .failureHandler(handlerLoginFail)
                //登录页面用户任意访问
                .permitAll()
                .and()
                .rememberMe()
                //rememberMe
                .rememberMeParameter("remember-me").userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .and()
                .authorizeRequests()
                //.anyRequest()
                //是否具有url资源的访问权限
                //.access("@rbacService.hasPermission(request,authentication)")
                .and()
                //Session 超时，最大在线数量
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(getSessionRegistry())
                .expiredUrl("/timeout")
                .and()
                .and().userDetailsService(userDetailsService)
                .exceptionHandling()
                .and()
                //注销
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
                .and().csrf().disable()
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);


    }

    @Bean
    public SessionRegistry getSessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    @Bean
    public ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}