package com.demo.angularpractice.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@ConfigurationProperties
@PropertySource(value = "classpath:properties/url.properties")
public class SecurityConst {
    @Value("${auth_endpoint_ignore}")
    public String[] ignore;
    @Value("${auth_endpoint_form_login}")
    public String login;
    @Value("${auth_endpoint_form_login_process}")
    public String loginProcess;
    @Value("${auth_endpoint_form_login_username_field}")
    public String loginUsernameField;
    @Value("${auth_endpoint_form_login_password_field}")
    public String loginPasswordField;
    @Value("${auth_endpoint_form_logout}")
    public String logout;
    @Value("${auth_endpoint_authorization_auth}")
    public String auth;
    @Value("${auth_endpoint_authorization_token}")
    public String token;
    @Value("${auth_endpoint_header_authorization}")
    public String authorization;
    @Value("${auth_endpoint_header_token}")
    public String headerToken;
    @Value("${auth_endpoint_api_url_root}")
    public String root;
}