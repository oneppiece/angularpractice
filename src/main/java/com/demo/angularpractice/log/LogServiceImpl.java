package com.demo.angularpractice.log;

import com.demo.angularpractice.entity.Log;
import com.demo.angularpractice.repository.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void insert(Log log) {
        logMapper.insert(log);
    }
}
