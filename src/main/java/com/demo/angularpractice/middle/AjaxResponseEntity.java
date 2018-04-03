package com.demo.angularpractice.middle;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Ajax 返回的实体信息
 *
 * @param <T>
 */
@Getter
@Setter
@Component
public class AjaxResponseEntity<T> {


    private Date requestTime;
    private Date returnTime;
    private boolean success = true;
    private T response;
    private ResponseEntity<T> responseEntity;
    private Map<String, String> errorMessage;

    public AjaxResponseEntity() {
        this.requestTime = new Date();
    }

    public AjaxResponseEntity(Date requestTime, Date returnTime, boolean success, T response, ResponseEntity<T> responseEntity, Map<String, String> errorMessage) {
        this.requestTime = requestTime;
        this.returnTime = returnTime;
        this.success = success;
        this.response = response;
        this.responseEntity = responseEntity;
        this.errorMessage = errorMessage;
    }
}
