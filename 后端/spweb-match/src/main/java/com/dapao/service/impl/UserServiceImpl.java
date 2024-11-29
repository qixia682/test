package com.dapao.service.impl;

import com.dapao.mapper.CaptchaMapper;
import com.dapao.mapper.UserMapper;
import com.dapao.mapper.MatchMapper;
import com.dapao.pojo.Captcha;
import com.dapao.pojo.LoginResponse;
import com.dapao.pojo.Result;
import com.dapao.pojo.User;
import com.dapao.service.UserService;
import com.dapao.utils.GetQQImgUtils;
import com.dapao.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CaptchaMapper captchaMapper;
    @Autowired
    private MatchMapper matchMapper;

    @Override
    public Result register(User user) {
        //验证验证码
        try {
            String captcha = user.getCaptcha();//用户收到的验证码
            Captcha captchaNum = captchaMapper.getByEmail(user.getEmail()); //数据库中保存的验证码
            if(!Objects.equals(captchaNum.getCaptcha(), captcha)) {
                return Result.error("注册失败，请检查邮箱和验证码");
            }
            //获取用户头像
            String email = user.getEmail();
            String QQImg = GetQQImgUtils.GetQQImgUrl(email.replace("@qq.com", ""));
            user.setImg(QQImg);
            userMapper.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("注册失败，请检查邮箱和验证码");
        }
        return Result.success("注册成功");
    }

    @Override
    public Result login(User user) {
        User u = userMapper.getByEmailAndPassword(user);
        if(u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getUsername());
            claims.put("email",u.getEmail());

            String jwt = JwtUtils.generateJwt(claims);
            LoginResponse loginResponse = new LoginResponse(u,jwt);
            return Result.success(loginResponse);
        }
        return Result.error("登录失败，请检查邮箱和密码是否正确");
    }

    @Override
    public Result updateUser(User user) {
        userMapper.updateUser(user);
        return Result.success(userMapper.getById(user.getId()));
    }
}
