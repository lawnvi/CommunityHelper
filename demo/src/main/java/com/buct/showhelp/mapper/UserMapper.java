package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //登录注册
    @Select("select * from users where email = #{email} and password = #{password}")
    Users userLogin(@Param("email") String email, @Param("password") String password);

//    name number password tel address email school ip picturePath
    @Insert("insert into users (name, password, email, picturePath) values(#{name}, #{password}, #{email}, #{picturePath})")
    int userRegister(Users users);

    @Update("update users set name = #{name}, tel = #{tel}, address = #{address}, school = #{school}, picturePath = #{picturePath} where id = #{id}")
    int updateUser(Users users);

    @Select("select * from users where id = #{id}")
    Users findUserById(int id);

    @Select("select * from users where email = #{email}")
    Users findUserByEmail(String email);

    //change password
    @Update("update users set password = #{psw} where id = #{id}")
    int changePassword(int id, String psw);

    @Select("select * from users where name like #{keyword}")
    List<Users> findUserByName(String keyword);

    @Select("select * from users")
    List<Users> findAllUser();

}
