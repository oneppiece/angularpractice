package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
}