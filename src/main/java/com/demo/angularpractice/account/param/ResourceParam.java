package com.demo.angularpractice.account.param;


import com.demo.angularpractice.account.domain.BaseReqParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceParam extends BaseReqParam {
    private Integer id;
    private String name;
    private String url;
    private String description;
    private Integer pid;
}