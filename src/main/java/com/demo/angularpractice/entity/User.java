package com.demo.angularpractice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseEntity{

    private static final long serialVersionUID = -1296966996840729616L;
    private Integer id;

    private String name;

    private String username;

    private String password;

}