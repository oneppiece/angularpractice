package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.account.domain.AppException;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * 全局异常处理,将捕获到的异常都在此类中处理。不包含404，500。
 *
 * @author dzy
 */
@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class InternalAdvinceController {

    /**
     * @param e 自定义异常外的程序异常
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public AjaxResponseEntity<Object> httpException(Exception e) {
        log.error(Throwables.getStackTraceAsString(e));
        AjaxResponseEntity<Object> responseEntity = new AjaxResponseEntity<>();
        responseEntity.setMsg(Throwables.getStackTraceAsString(e));
        responseEntity.setSuccess(Boolean.FALSE);
        responseEntity.setReturnTime(new Date());
        responseEntity.setStatus(500);
        return responseEntity;
    }


    /**
     * 处理所有业务异常
     *
     * @param e 所有的自定义异常
     * @return
     */
    @ExceptionHandler(AppException.class)
    @ResponseBody
    public AjaxResponseEntity<Object> handleBusinessException(AppException e) {
        log.error(e.getMessage(), e);
        AjaxResponseEntity<Object> result = new AjaxResponseEntity<>();
        result.setSuccess(Boolean.FALSE);
        result.setMsg(Throwables.getStackTraceAsString(e));
        result.setReturnTime(new Date());
        result.setStatus(500);
        return result;
    }


}
