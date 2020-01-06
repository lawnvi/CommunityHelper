package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Admin;
import com.buct.showhelp.pojo.Notice;
import com.buct.showhelp.pojo.WebsiteLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 后台数据查看与管理
 */
@Mapper
public interface AdminMapper {
    /**
     * login and logout and register
     */
    @Insert("insert into admin (email, name, password, tel, picPath) values (#{email}, #{name}, #{password}, #{tel}, #{picPath})")
    int registerAdmin(Admin admin);

    @Update("update admin set name =  #{name}, tel = #{tel}, picPath = #{picPath} where id = #{id}")
    int updateAdmin(Admin admin);

    @Update("update admin set password = #{password} where id = #{id}")
    int changePassword(String password, int id);

    //login
    @Select("select * from admin where email = #{email} and password = #{password}")
    Admin findAdmin(String email, String password);

    @Select("select * from admin where id = #{id}")
    Admin findAdminById(int id);

    /**
     * Log 相关
     */
    @Insert("insert into websiteLog (date) values (date)")
    int addLog(String date);

    @Update("update websiteLog set user = #{user}, visit = #{visit}, goods = #{goods}, successDeal = #{successDeal}, failedDeal = #{failedDeal} where date = #{date}")
    int updateTodayLog(WebsiteLog log);

    @Select("select * from websiteLog")
    List<WebsiteLog> getAllLog();

    @Select("select * from websiteLog where id = #{id}")
    WebsiteLog getLogById(int id);

    @Select("select * from websiteLog where date = #{date}")
    WebsiteLog getLogByDate(String date);


    /**
     * Notice相关
     */

    @Insert("insert into notice (content, userId) values (#{content}, #{userId})")
    int addNotice(Notice notice);

    @Update("update notice set userId = #{userId}, content = #{content}, status = #{status}, endTime = #{endTime} where id = #{id}")
    int updateNotice(Notice notice);

    @Select("select * from notice")
    List<Notice> getAllNotice();

    @Select("select * from notice where userId = #{userId}")
    List<Notice> getNoticeByUserId(int userId);

    /**
     * 流量数据
     */
    @Select("select count(*) from users")
    int getUserNumber();

    @Select("select count(*) from goods")
    int getGoodsNumber();

    @Select("select count(*) from orders where status = 'success' ")
    int getOrderNumber();

    /**
     * 管理用户/商品/交易
     */
}
