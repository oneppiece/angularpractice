package com.demo.angularpractice.middle;

import com.demo.angularpractice.account.domain.AppException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class BaseCheckComponent {
    public <T> void checkNull(T t) {
        if (ObjectUtils.isEmpty(t)) {
            throw new AppException(AppErrorCodeEnum.ERROR_CODE_000002.getErrorCode(), AppErrorCodeEnum.ERROR_CODE_000002.getErrorDesc());
        }
    }

}
