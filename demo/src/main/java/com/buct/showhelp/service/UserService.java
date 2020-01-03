package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Users;

public interface UserService {
    Users userLogin(String userNumber, String password);

    int userRegister(String username, String password, String email);

    //更新个人信息
    int updateUser(Users users);

    int changePassword(int id, String psw);

    Users findUserById(int id);
}