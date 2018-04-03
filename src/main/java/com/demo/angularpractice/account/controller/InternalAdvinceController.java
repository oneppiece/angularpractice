package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.account.domain.AppException;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author dzy
 */
@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class InternalAdvinceController {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String exception404(HttpServletResponse response) {
        int status = response.getStatus();
        System.out.println(status);
        return "" + status;
    }


    /**
     * 处理所有业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public AjaxResponseEntity<Object> handleBusinessException(AppException e) {
        log.error(e.getMessage(), e);
        AjaxResponseEntity<Object> result = new AjaxResponseEntity<>();
        Map<String, String> errorMsg = Maps.newHashMap();
        errorMsg.put("errorCode", e.getCode().toString());
        errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
        result.setSuccess(Boolean.FALSE);
        result.setErrorMessage(errorMsg);
        return result;
    }


}
