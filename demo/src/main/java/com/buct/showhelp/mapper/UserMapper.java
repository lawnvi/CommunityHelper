package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    //登录注册
    @Select("select * from users where number = #{number}")
    Users userLogin(@Param("number") String number, @Param("passward") String password);

    @Insert("insert into users (name, password) values(#{name}, #{password})")
    int userRegister(@Param("name") String name, @Param("password") String password);

    //CURD
}
