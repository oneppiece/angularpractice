package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    Resource selectByPrimaryKey(Integer id);

    List<Resource> selectAll();

    int updateByPrimaryKey(Resource record);
}