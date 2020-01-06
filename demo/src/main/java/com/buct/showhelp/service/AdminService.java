package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Admin;
import com.buct.showhelp.pojo.Notice;
import com.buct.showhelp.pojo.WebsiteLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminService {

    /**
     * login register
     */
    int registerAdmin(Admin admin);

    int updateAdmin(Admin admin);

    int changePassword(String password, int id);

    //login
    Admin findAdmin(String email, String password);

    Admin findAdminById(int id);

    /**
     * Log
     */
    int addLog(String date);

    int updateTodayLog(WebsiteLog log);

    List<WebsiteLog> getAllLog();

    WebsiteLog getLogById(int id);

    WebsiteLog getLogByDate(String date);

    /**
     * Notice相关
     */

    int addNotice(Notice notice);

    int updateNotice(Notice notice);

    List<Notice> getAllNotice();

    List<Notice> getNoticeByUserId(int userId);

    /**
     * 流量数据
     */
    int getUserNumber();

    int getGoodsNumber();

    int getOrderNumber();

    /**
     * user/goods/order
     */

}
