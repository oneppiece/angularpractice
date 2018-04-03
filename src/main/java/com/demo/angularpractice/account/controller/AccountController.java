package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.account.domain.AppException;
import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.result.ResourceResult;
import com.demo.angularpractice.account.result.RoleResult;
import com.demo.angularpractice.account.service.AccountService;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * auth 管理
 *
 * @author dzy
 */
@RestController
@RequestMapping("/api/account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/roles")
    public AjaxResponseEntity<List<RoleResult>> roles(@RequestBody RoleParam roleParam) {
        log.info("call /api/account/roles param:", roleParam.toString());
        AjaxResponseEntity<List<RoleResult>> result = new AjaxResponseEntity<>();
        List<RoleResult> queryResult = Lists.newArrayList();
        try {
            log.info("call /api/account/roles param:", roleParam.toString());
            queryResult = accountService.getRolesByCondition(roleParam);
            result.setResponseEntity(new ResponseEntity<>(queryResult, HttpStatus.OK));
            result.setResponse(queryResult);
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            Map<String, String> errorMsg = Maps.newHashMap();
            errorMsg.put("errorCode", e.getCode().toString());
            errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMsg);
            log.error("call /api/account/roles has exception. param:,result:,stack:", roleParam.toString(), queryResult.toString(), Throwables.getStackTraceAsString(e));
        } finally {
            result.setReturnTime(new Date());
        }
        return result;
    }

    @GetMapping("/roles/user")
    public AjaxResponseEntity<List<RoleResult>> roles(@RequestBody UserParam userParam) {
        log.info("call /api/account/roles param:", userParam.toString());
        AjaxResponseEntity<List<RoleResult>> result = new AjaxResponseEntity<>();
        List<RoleResult> queryResult = Lists.newArrayList();
        try {
            log.info("call /api/account/roles param:", userParam.toString());
            queryResult = accountService.getRolesByUser(userParam);
            result.setResponseEntity(new ResponseEntity<>(queryResult, HttpStatus.OK));
            result.setResponse(queryResult);
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            Map<String, String> errorMsg = Maps.newHashMap();
            errorMsg.put("errorCode", e.getCode().toString());
            errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMsg);
            log.error("call /api/account/roles has exception. param:,result:,stack:", userParam.toString(), queryResult.toString(), Throwables.getStackTraceAsString(e));
        } finally {
            result.setReturnTime(new Date());
        }
        return result;
    }

    @GetMapping("/resources/role")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody RoleParam roleParam) {
        log.info("call /api/account/resources param:", roleParam.toString());
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = Lists.newArrayList();
        try {
            log.info("call /api/account/resources param:", roleParam.toString());
            queryResult = accountService.getResourcesByRole(roleParam);
            result.setResponseEntity(new ResponseEntity<>(queryResult, HttpStatus.OK));
            result.setResponse(queryResult);
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            Map<String, String> errorMsg = Maps.newHashMap();
            errorMsg.put("errorCode", e.getCode().toString());
            errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMsg);
            log.error("call /api/account/resources has exception. param:,result:,stack:", roleParam.toString(), queryResult.toString(), Throwables.getStackTraceAsString(e));
        } finally {
            result.setReturnTime(new Date());
        }
        return result;
    }

    @GetMapping("/resources/user")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody UserParam userParam) {
        log.info("call /api/account/resources param:", userParam.toString());
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = Lists.newArrayList();
        try {
            log.info("call /api/account/resources param:", userParam.toString());
            queryResult = accountService.getResourcesByUser(userParam);
            result.setResponseEntity(new ResponseEntity<>(queryResult, HttpStatus.OK));
            result.setResponse(queryResult);
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            Map<String, String> errorMsg = Maps.newHashMap();
            errorMsg.put("errorCode", e.getCode().toString());
            errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMsg);
            log.error("call /api/account/resources has exception. param:,result:,stack:", userParam.toString(), queryResult.toString(), Throwables.getStackTraceAsString(e));
        } finally {
            result.setReturnTime(new Date());
        }
        return result;
    }

    @GetMapping("/resources")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody ResourceParam resourceParam) {
        log.info("call /api/account/resources param:", resourceParam.toString());
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = Lists.newArrayList();
        try {
            log.info("call /api/account/resources param:", resourceParam.toString());
            queryResult = accountService.getResourcesByCondition(resourceParam);
            result.setResponseEntity(new ResponseEntity<>(queryResult, HttpStatus.OK));
            result.setResponse(queryResult);
            result.setSuccess(Boolean.TRUE);
        } catch (AppException e) {
            Map<String, String> errorMsg = Maps.newHashMap();
            errorMsg.put("errorCode", e.getCode().toString());
            errorMsg.put("errorDesc", Throwables.getStackTraceAsString(e));
            result.setSuccess(Boolean.FALSE);
            result.setErrorMessage(errorMsg);
            log.error("call /api/account/resources has exception. param:,result:,stack:", resourceParam.toString(), queryResult.toString(), Throwables.getStackTraceAsString(e));
        } finally {
            result.setReturnTime(new Date());
        }
        return result;
    }


}
