package com.store.cosystore.service;

import com.itextpdf.text.DocumentException;
import com.store.cosystore.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Service
public class MailSender {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    CheckCreator checkCreator;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    public void sendWithCheck(Order order) throws MessagingException, IOException, DocumentException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(order.getEmail());
        helper.setSubject("Чек заказа");
        helper.setText("");
        helper.addAttachment("Check.pdf", checkCreator.getFile(order));
        mailSender.send(message);
    }
}
