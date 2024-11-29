package com.dapao.service;

import com.dapao.pojo.Result;
import com.dapao.pojo.User;

public interface UserService {
    Result register(User user);

    Result login(User user);

    Result updateUser(User user);
}
