package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.RoleResource;

import java.util.List;

public interface RoleResourceMapper {
    int insert(RoleResource record);

    List<RoleResource> selectAll();
}