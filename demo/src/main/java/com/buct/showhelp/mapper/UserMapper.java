package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //登录注册
    @Select("select * from users where email = #{email} and password = #{password}")
    Users userLogin(@Param("email") String email, @Param("password") String password);

//    name number password tel address email school ip picturePath
    @Insert("insert into users (name, password, email, picture_path) values(#{name}, #{password}, #{email}, '')")
    int userRegister(@Param("name") String name, @Param("password") String password, @Param("email") String email);

    @Update("update users set name = #{name}, tel = #{tel}, address = #{address}, school = #{school}, picture_path = #{picturePath} where id = #{id}")
    int updateUser(Users users);

    @Select("select * from users where id = #{id}")
    Users findUserById(int id);

    //change password
    @Update("update users set password = #{psw} where id = #{id}")
    int changePassword(int id, String psw);
}
