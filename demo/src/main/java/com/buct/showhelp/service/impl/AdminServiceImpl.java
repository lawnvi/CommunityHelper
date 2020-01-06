package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.AdminMapper;
import com.buct.showhelp.pojo.Admin;
import com.buct.showhelp.pojo.Notice;
import com.buct.showhelp.pojo.WebsiteLog;
import com.buct.showhelp.service.AdminService;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public int registerAdmin(Admin admin) {
        return adminMapper.registerAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int changePassword(String password, int id) {
        return adminMapper.changePassword(password, id);
    }

    @Override
    public Admin findAdmin(String email, String password) {
        return adminMapper.findAdmin(email, password);
    }

    @Override
    public Admin findAdminById(int id) {
        return adminMapper.findAdminById(id);
    }

    @Override
    public int addLog(String date) {
        return adminMapper.addLog(date);
    }

    @Override
    public int updateTodayLog(WebsiteLog log) {
        return adminMapper.updateTodayLog(log);
    }

    @Override
    public List<WebsiteLog> getAllLog() {
        return adminMapper.getAllLog();
    }

    @Override
    public WebsiteLog getLogById(int id) {
        return adminMapper.getLogById(id);
    }

    @Override
    public WebsiteLog getLogByDate(String date) {
        return adminMapper.getLogByDate(date);
    }

    @Override
    public int addNotice(Notice notice) {
        return adminMapper.addNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return adminMapper.updateNotice(notice);
    }

    @Override
    public List<Notice> getAllNotice() {
        return adminMapper.getAllNotice();
    }

    @Override
    public List<Notice> getNoticeByUserId(int userId) {
        return adminMapper.getNoticeByUserId(userId);
    }

    @Override
    public int getUserNumber() {
        return adminMapper.getUserNumber();
    }

    @Override
    public int getGoodsNumber() {
        return adminMapper.getGoodsNumber();
    }

    @Override
    public int getOrderNumber() {
        return adminMapper.getOrderNumber();
    }
}
