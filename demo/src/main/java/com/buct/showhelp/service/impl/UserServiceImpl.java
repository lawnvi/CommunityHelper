package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.UserMapper;
import com.buct.showhelp.pojo.Users;
import com.buct.showhelp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
//@RequestMapping("user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;


    //用户登录
    @Override
    public Users userLogin(String email,String password){
        return usermapper.userLogin(email, password);
    }

    //注册新用户
    @Override
    public int userRegister(String username, String password, String email){
        return usermapper.userRegister(username, password, email);
    }

    @Override
    public int updateUser(Users users) {
        return usermapper.updateUser(users);
    }

    @Override
    public int changePassword(int id, String psw) {
        return usermapper.changePassword(id, psw);
    }

    @Override
    public Users findUserById(int id) {
        return usermapper.findUserById(id);
    }

    @Override
    public List<Users> findUserByName(String keyword) {
        return usermapper.findUserByName(keyword);
    }

    @Override
    public List<Users> findAllUser() {
        return usermapper.findAllUser();
    }
}
