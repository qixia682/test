package com.dapao.controller;

import com.dapao.pojo.Result;
import com.dapao.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public Result email(String email) {
        log.info("发送邮件：{}---email", email);
        return emailService.sendEmail(email);
    }

}
