package com.scm.scm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.scm.scm.services.EmailServices;

@Service
public class EmailServicesImpl implements EmailServices{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String body) {

       SimpleMailMessage mailMessage=new SimpleMailMessage();

       mailMessage.setTo(to);
       mailMessage.setSubject(subject);
       mailMessage.setText(body);
       mailMessage.setFrom(domainName);

       javaMailSender.send(mailMessage);
        
    }

}
