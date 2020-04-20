package com.book.es.impl;

import com.book.es.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailImpl implements EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Override
    public boolean sendEmail(String name, String email, String bookName, Date shouldReturnDay) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setSubject("还书通知");
            helper.setText(name+"同学,您应于"+(new SimpleDateFormat("yyyy年MM月dd日").format(shouldReturnDay))+"将"+bookName+"归还于本图书馆");
            helper.setTo(email);
            helper.setFrom("1925109128@qq.com");
        } catch (MessagingException e) {
            return false;
        }
        mailSender.send(mimeMessage);
        return true;

    }



}
