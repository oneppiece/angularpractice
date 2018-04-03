package com.demo.angularpractice.middle;

import com.demo.angularpractice.account.domain.AppException;
import com.demo.angularpractice.account.param.RoleParam;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 方法参数校验
 *
 * @author dzy
 */
@Component
public class UserCheckComponent extends BaseCheckComponent {

    public void checkGetRolesByCondition(RoleParam roleParam) {
        if (Objects.isNull(roleParam)) {
            throw new AppException(AppErrorCodeEnum.ERROR_CODE_000002.getErrorCode(), AppErrorCodeEnum.ERROR_CODE_000002.getErrorDesc());
        }
    }
}
