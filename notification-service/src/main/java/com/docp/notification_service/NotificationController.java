package com.docp.notification_service;


import com.docp.notification_service.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private MailService mailService;
    @GetMapping
    public void sendNotification() {
        mailService.sendEmail("divyao.cse.22.jisu@gmail.com","test email", "body");

    }
}
