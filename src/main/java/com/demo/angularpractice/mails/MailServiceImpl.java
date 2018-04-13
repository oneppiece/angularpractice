package com.demo.angularpractice.mails;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单邮件
     *
     * @param sendTo  收件人地址
     * @param title   邮件标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送简单邮件
     *
     * @param sendTo      收件人地址
     * @param title       邮件标题
     * @param content     邮件内容
     * @param attachments
     */
    @Override
    public void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);

            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getKey(), new FileSystemResource(pair.getValue()));
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }

    /**
     * 发送模板邮件
     *
     * @param sendTo      收件人地址
     * @param title       邮件标题
     * @param content
     * @param attachments
     */
    @Override
    public void sendTemplateMail(String sendTo, String title, Map<String, Object> content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(sendTo);
            helper.setSubject(title);

            String text = (String) content.getOrDefault("a", "空的");
            helper.setText(text, true);

            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getKey(), new FileSystemResource(pair.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
