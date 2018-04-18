package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * AjaxAwareAuthenticationSuccessHandler
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 3, 2016
 */
@Component("ajaxSuccessHandler")
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private JWTUtil jwtUtil;

    public AjaxAwareAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = (String) authentication.getPrincipal();
        UserParam userContext = new UserParam();
        userContext.setUsername(username);
        String accessToken = jwtUtil.generateToken(userContext);
        String refreshToken = jwtUtil.refreshToken(accessToken);

        Map<String, String> tokenMap = Maps.newHashMap();
        tokenMap.put("token", accessToken);
        tokenMap.put("refreshToken", refreshToken);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        mapper.writeValue(response.getWriter(), tokenMap);

        clearAuthenticationAttributes(request);
    }


    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
