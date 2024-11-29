package com.dapao.controller;

import com.dapao.pojo.Result;
import com.dapao.pojo.User;
import com.dapao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/post/register")
    public Result register(@RequestBody User user) {
        log.info("注册：user：{}---register", user);
        return userService.register(user);
    }

    @PostMapping("/post/login")
    public Result login(@RequestBody User user) {
        log.info("登录：user：{}---login", user);
        return userService.login(user);
    }

    @PostMapping("/post/update")
    public Result updateUser(@RequestBody User user) {
        log.info("修改用户信息：user：{}---updateUser", user);
        return userService.updateUser(user);
    }

}
