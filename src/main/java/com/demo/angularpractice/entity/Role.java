package com.demo.angularpractice.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends BaseEntity{
    private static final long serialVersionUID = -5556960555784583626L;
    private Integer id;

    private String name;

}