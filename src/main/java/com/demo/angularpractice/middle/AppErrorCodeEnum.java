package com.demo.angularpractice.middle;

import com.google.common.base.Objects;
import lombok.Getter;

/**
 * 错误信息
 *
 * @author dzy
 */
public enum AppErrorCodeEnum {
    //以下为通用错误码
    ERROR_CODE_000000("000000", "成功!"),
    ERROR_CODE_404("404", "找不到页面"),
    ERROR_CODE_000001("000001", "参数非法！"),
    ERROR_CODE_000002("000002", "参数不能为空！"),
    ERROR_CODE_000003("000003", "未查询到数据！"),
    ERROR_CODE_000004("000004", "没有用户名！"),
    ERROR_CODE_000005("000005", "旧密码不正确！"),
    ERROR_CODE_000006("000006", "没有旧密码！"),
    ERROR_CODE_000007("000007", "没有新密码！"),
    ERROR_CODE_000008("000008", "没有该用户！"),
    ERROR_CODE_FFFFFF("111111", "程序异常(itr_dao)!"),
    ERROR_CODE_EEEEEE("222222", "程序异常(itr_service)!"),
    ERROR_CODE_333333("333333", "系统异常!新增失败!"),
    ERROR_CODE_444444("444444", "系统异常!删除失败!"),
    ERROR_CODE_555555("555555", "系统异常!更新失败!"),
    ERROR_CODE_666666("666666", "缺少参数");

    @Getter
    private String errorCode;
    @Getter
    private String errorDesc;

    AppErrorCodeEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public static String explain(String errorCode) {
        for (AppErrorCodeEnum appCoreErrorCode : AppErrorCodeEnum.values()) {
            if (Objects.equal(errorCode, appCoreErrorCode.errorCode)) {
                return appCoreErrorCode.errorDesc;
            }
        }
        return errorCode;
    }

}
