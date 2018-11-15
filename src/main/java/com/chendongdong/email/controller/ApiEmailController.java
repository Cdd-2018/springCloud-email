package com.chendongdong.email.controller;

import com.chendongdong.email.config.EmailConfig;
import com.chendongdong.email.domain.EmailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiEmailController {

    @Autowired
    EmailConfig mc;

    @PostMapping("sendEmail")
    public void sendEmail(@RequestBody EmailEntity email) throws Exception {
        email.setReceiver(email.getReceiver());
        email.setContent(email.getContent());
        email.setSubject(email.getSubject());
        mc.sendSimpleMail(email);
    }
}
