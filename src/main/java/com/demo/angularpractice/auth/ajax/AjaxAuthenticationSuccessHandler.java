package com.demo.angularpractice.auth.ajax;

import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.util.JWTUtil;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Date;

/**
 * AjaxAuthenticationSuccessHandler
 *
 * @author vladimir.stankovic
 * <p>
 * Aug 3, 2016
 */
@Component("ajaxSuccessHandler")
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private JWTUtil jwtUtil;

    public AjaxAuthenticationSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        String username = (String) authentication.getPrincipal();
        UserParam userContext = new UserParam();
        userContext.setUsername(username);
        String token = jwtUtil.generateToken(userContext);
        AjaxResponseEntity<String> result = new AjaxResponseEntity<>();
        result.setMsg("登陆成功！");
        result.setSuccess(Boolean.TRUE);
        result.setReturnTime(new Date());
        result.setStatus(200);
        result.setResponse(token);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");

        mapper.writeValue(response.getWriter(), result);

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
