package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    Resource selectByPrimaryKey(Integer id);

    List<Resource> selectAll();

    List<Resource> selectResourceByUserId(@Param("userId") Integer userId);

    int updateByPrimaryKey(Resource record);
}