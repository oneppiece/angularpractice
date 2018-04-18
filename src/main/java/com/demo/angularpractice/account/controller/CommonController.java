package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.auth.components.AccountService;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Objects;

/**
 * @author dzy
 */
@org.springframework.stereotype.Controller
@Slf4j
public class CommonController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute(name = "userParam") UserParam userParam, @RequestParam(name = "error", required = false) String error) {
        return "login";
    }

    @RequestMapping(value = "/")
    public String index(@ModelAttribute(name = "sysUserParam") UserParam userParam) {
        return "login";
    }

    @RequestMapping(value = "/whoami")
    @ResponseBody
    public Object whoami() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 修改密码
     *
     * @param userInfoParam
     * @return
     */
    @RequestMapping(value = "/changePassword")
    @ResponseBody
    public AjaxResponseEntity<UserParam> changePassword(Principal principal, UserParam userInfoParam) {
        AjaxResponseEntity<UserParam> result = new AjaxResponseEntity<>();
        userInfoParam.setUsername(principal.getName());
        result.setReturnTime(new Date());
        return result;
    }


    /**
     * session 超时
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/timeout")
    public void sessionTimeout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = "x-requested-with";
        if (Objects.isNull(request.getHeader(s))
                && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
            response.getWriter().print("timeout");
            response.getWriter().close();
        } else {
            response.sendRedirect("/login");
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/test2")
    public String test2() {
        int i = 1 / 0;
        return "/errorPage/404";
    }
}
