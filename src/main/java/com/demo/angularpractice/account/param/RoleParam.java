package com.demo.angularpractice.account.param;


import com.demo.angularpractice.account.domain.BaseReqParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleParam extends BaseReqParam {
    private Integer id;
    private String name;
}
