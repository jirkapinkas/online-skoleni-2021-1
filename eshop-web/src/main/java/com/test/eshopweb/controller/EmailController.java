package com.test.eshopweb.controller;

import com.test.eshopweb.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@Slf4j
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    // http://localhost:8080/sendEmails
    @GetMapping("/sendEmails") // POZOR! TOTO BY MELO BYT @PostMapping, PROTOZE GET OPERACE MAJI BYT IDEMPOTENT!!!
    public void sendEmails() {
        IntStream.range(0, 100)
                .forEach(emailService::sendEmail);
    }

}
