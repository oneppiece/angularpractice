package com.demo.angularpractice.middle;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Ajax 返回的实体信息
 *
 * @param <T>
 * @author dzy
 */
@Getter
@Setter
@Component
public class AjaxResponseEntity<T> {


    private int status;
    private boolean success;
    private Date requestTime;
    private Date returnTime;
    private T response;
    private String msg;

    public AjaxResponseEntity() {
        this.requestTime = new Date();
        this.success = Boolean.FALSE;
    }


}
