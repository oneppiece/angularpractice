package com.demo.angularpractice.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Autowired
    private LogService logService;


}
