package com.demo.angularpractice.account;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@org.springframework.stereotype.Controller
@Slf4j
public class CommonController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute(name = "sysUserParam") SysUserParam sysUserParam, @RequestParam(name = "error", required = false) String error) {
        return "login";
    }

    @RequestMapping(value = "/")
    public String index(@ModelAttribute(name = "sysUserParam") SysUserParam sysUserParam) {
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
    public AjaxResponseEntity<SysUserParam> changePassword(Principal principal, SysUserParam userInfoParam) {
        log.info("PublicController ->Call changePassword.Paramter:{}" + userInfoParam);
        AjaxResponseEntity<SysUserParam> result = new AjaxResponseEntity<>();
        result.setRequestTime(new Date());
        try {
            userInfoParam.setUsername(principal.getName());
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            HashMap<String, String> errorMap = Maps.newHashMap();
            errorMap.put("errorCode", e.getCode());
            errorMap.put("errorMessage", e.getMessage());
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMap);
            log.error("PublicController ->Call changePassword has Exception.Paramter:{},result:{}.ErrorMessage:{}", Objects.isNull(userInfoParam) ? null : userInfoParam.toString(),
                    result, e.getMessage());
        } catch (Exception e) {
            HashMap<String, String> errorMap = Maps.newHashMap();
            errorMap.put("errorCode", "000000");
            errorMap.put("errorMessage", e.getMessage());
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMap);
            log.error("PublicController ->Call changePassword has Exception.Paramter:{},result:{}.ErrorMessage:{}", Objects.isNull(userInfoParam) ? null : userInfoParam.toString(),
                    result, e.getMessage());
        } finally {
            result.setReturnTime(new Date());
        }
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
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase(
                "XMLHttpRequest")) { // ajax 超时处理
            response.getWriter().print("timeout");  //设置超时标识
            response.getWriter().close();
        } else {
            response.sendRedirect("/login");
        }
    }
}
