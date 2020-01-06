package com.buct.showhelp.utils;

import com.buct.showhelp.pojo.Admin;
import com.buct.showhelp.pojo.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Users getUserSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Users) session.getAttribute("Session_user");
    }

    public static Admin getAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Admin) session.getAttribute("AdminObj");
    }

    public static String getTime(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }
}
