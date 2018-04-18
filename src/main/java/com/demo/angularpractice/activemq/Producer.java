//package com.demo.angularpractice.activemq;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.jms.Queue;
//
//
//@RestController
//public class Producer {
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//    @Autowired
//    private Queue queue;
//
//    @GetMapping("/sendMsg")
//    public void send(String msg) {
//        jmsMessagingTemplate.convertAndSend("simple.queue", msg);
//    }
//}
