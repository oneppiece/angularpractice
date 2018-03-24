package com.demo.angularpractice.account;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * 自定义异常
 * Created by Administrator on 22:41.
 */
@Component
public class AppException extends RuntimeException {


    @Getter
    private String code;
    @Getter
    private String message;

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AppException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public AppException(String message, String code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public AppException(String message, Throwable cause, String code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public AppException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public AppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }
}
