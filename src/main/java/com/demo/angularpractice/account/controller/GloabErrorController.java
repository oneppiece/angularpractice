package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.middle.AjaxResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * liyan-下午9:36
 *
 * @author liyan
 **/
@Controller
@Slf4j
public class GloabErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public AjaxResponseEntity<Object> handleError(HttpServletRequest request, HttpServletResponse response) {
        AjaxResponseEntity<Object> ajaxResponseEntity = new AjaxResponseEntity<>();
        int status = response.getStatus();
        String msg = "";
        ajaxResponseEntity.setStatus(status);
        ajaxResponseEntity.setSuccess(Boolean.FALSE);
        ajaxResponseEntity.setReturnTime(new Date());
        switch (status) {
            case 404:
                msg = "找不到页面";
                break;
            case 500:
                msg = "服务器端错误";
                break;
            case 403:
                msg = "无权访问";
        }
        ajaxResponseEntity.setMsg(msg);
        return ajaxResponseEntity;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
