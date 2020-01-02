package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.UserMapper;
import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
//@RequestMapping("user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;


    //用户登录
    @Override
    public Users userLogin(String email,String password){
        Users user = usermapper.userLogin(email, password);
        return user;
    }

    //注册新用户
    @Override
    public int userRegister(String username,String password){
        return usermapper.userRegister(username, password);
    }
}
