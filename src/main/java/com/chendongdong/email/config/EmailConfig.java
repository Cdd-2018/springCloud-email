package com.chendongdong.email.config;

import com.chendongdong.email.domain.EmailEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.account}")
    private String account;
    @Value("${spring.mail.password}")
    private String password;

    @Bean(name = "JavaMailSenderImpl")
    public JavaMailSenderImpl getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setUsername(account);
        javaMailSender.setPassword(password);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }

    public void sendSimpleMail(EmailEntity email) throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(account);
        String receiver = email.getReceiver();
        String receivers[] = receiver.split(";");
        simpleMailMessage.setTo(receivers);
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getContent());
        try {
            getMailSender().send(simpleMailMessage);
        }catch (MailException e){
            System.out.println("邮件发送错误");
            throw new Exception("邮件发送错误");
        }
    }

}
