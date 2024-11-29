package com.dapao.service.impl;

import com.dapao.mapper.CaptchaMapper;
import com.dapao.pojo.Captcha;
import com.dapao.pojo.Result;
import com.dapao.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private CaptchaMapper captchaMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Result sendEmail(String email) {
        try {
            // 生成六位数的随机验证码
            Random random = new Random();
            String captchaNumber = String.valueOf(random.nextInt(900000) + 100000);
            //验证该邮箱是否已存在，存入验证码
            Captcha captcha = captchaMapper.getByEmail(email);
            if (captcha == null) {
                //不存在,插入
                captcha = new Captcha(email,captchaNumber);
                captchaMapper.insert(captcha);
            } else {
                //存在,更新
                captcha.setCaptcha(captchaNumber);
                captchaMapper.update(captcha);
            }
            //发送邮件
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("3474278753@qq.com");
            message.setTo(email);
            message.setSubject("竞赛查询系统验证码");
            message.setText(String.format("验证码为：%s",captchaNumber));
            mailSender.send(message);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("请检查邮箱");
        }
    }
}
