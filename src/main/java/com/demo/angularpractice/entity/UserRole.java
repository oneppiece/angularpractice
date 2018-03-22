package com.demo.angularpractice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = -7174061813566686889L;
    private Integer userId;

    private Integer roleId;


}