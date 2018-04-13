package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    Log selectByPrimaryKey(Integer id);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);
}