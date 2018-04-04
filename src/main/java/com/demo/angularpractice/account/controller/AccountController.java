package com.demo.angularpractice.account.controller;

import com.demo.angularpractice.account.param.ResourceParam;
import com.demo.angularpractice.account.param.RoleParam;
import com.demo.angularpractice.account.param.UserParam;
import com.demo.angularpractice.account.result.ResourceResult;
import com.demo.angularpractice.account.result.RoleResult;
import com.demo.angularpractice.account.service.AccountService;
import com.demo.angularpractice.middle.AjaxResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        AjaxResponseEntity<List<RoleResult>> result = new AjaxResponseEntity<>();
        List<RoleResult> queryResult = accountService.getRolesByCondition(roleParam);
        result.setStatus(Objects.isNull(queryResult) ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        result.setResponse(queryResult);
        result.setReturnTime(new Date());
        return result;
    }

    @GetMapping("/roles/user")
    public AjaxResponseEntity<List<RoleResult>> roles(@RequestBody UserParam userParam) {
        AjaxResponseEntity<List<RoleResult>> result = new AjaxResponseEntity<>();
        List<RoleResult> queryResult = accountService.getRolesByUser(userParam);
        result.setStatus(Objects.isNull(queryResult) ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        result.setResponse(queryResult);
        result.setReturnTime(new Date());
        return result;
    }

    @GetMapping("/resources/role")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody RoleParam roleParam) {
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = accountService.getResourcesByRole(roleParam);
        result.setStatus(Objects.isNull(queryResult) ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        result.setResponse(queryResult);
        result.setReturnTime(new Date());
        return result;
    }

    @GetMapping("/resources/user")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody UserParam userParam) {
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = accountService.getResourcesByUser(userParam);
        result.setStatus(Objects.isNull(queryResult) ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        result.setResponse(queryResult);
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    @GetMapping("/resources")
    public AjaxResponseEntity<List<ResourceResult>> resources(@RequestBody ResourceParam resourceParam) {
        AjaxResponseEntity<List<ResourceResult>> result = new AjaxResponseEntity<>();
        List<ResourceResult> queryResult = accountService.getResourcesByCondition(resourceParam);
        result.setStatus(Objects.isNull(queryResult) ? HttpStatus.NO_CONTENT.value() : HttpStatus.OK.value());
        result.setResponse(queryResult);
        result.setSuccess(Boolean.TRUE);
        return result;
    }


}
