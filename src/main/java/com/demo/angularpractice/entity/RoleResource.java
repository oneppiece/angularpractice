package com.demo.angularpractice.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResource extends BaseEntity{
    private static final long serialVersionUID = -3809481342082565710L;
    private Integer roleId;

    private Integer resourceId;

}