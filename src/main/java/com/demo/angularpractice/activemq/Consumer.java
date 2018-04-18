//package com.demo.angularpractice.activemq;
//
//import com.demo.angularpractice.entity.Log;
//import com.demo.angularpractice.log.LogService;
//import com.demo.angularpractice.mails.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Consumer {
//    @Autowired
//    private MailService mailService;
//    @Autowired
//    private LogService logService;
//
//    @JmsListener(destination = "simple.queue")
//    public void receive1(String text) {
//        mailService.sendSimpleMail("164408623@qq.com", "主题", text);
//        Log log = new Log();
//        log.setContent(text);
//        logService.insert(log);
//
//    }
//
//}
