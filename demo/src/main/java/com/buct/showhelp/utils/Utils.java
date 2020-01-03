package com.buct.showhelp.utils;

import com.buct.showhelp.pojo.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    public static Users getUserSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Users) session.getAttribute("Session_user");
    }
}
