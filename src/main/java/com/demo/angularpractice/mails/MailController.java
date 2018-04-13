package com.demo.angularpractice.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/mail")
    public void sendMail() {
        mailService.sendSimpleMail("954232231@qq.com", "测试一笔", "这是内容。");
    }
}
