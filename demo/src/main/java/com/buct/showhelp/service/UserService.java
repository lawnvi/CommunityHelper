package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Users;

public interface UserService {
    Users userLogin(String userNumber, String password);

    int userRegister(String username, String password, String email);
}