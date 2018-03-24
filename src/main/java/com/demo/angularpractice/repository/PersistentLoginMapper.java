package com.demo.angularpractice.repository;

import com.demo.angularpractice.entity.PersistentLogin;
import java.util.List;

public interface PersistentLoginMapper {
    int deleteByPrimaryKey(String series);

    int insert(PersistentLogin record);

    PersistentLogin selectByPrimaryKey(String series);

    List<PersistentLogin> selectAll();

    int updateByPrimaryKey(PersistentLogin record);
}