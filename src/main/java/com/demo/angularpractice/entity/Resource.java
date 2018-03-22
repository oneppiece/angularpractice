package com.demo.angularpractice.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource extends BaseEntity {
    private static final long serialVersionUID = 1274603366550643621L;
    private Integer id;

    private String name;

}