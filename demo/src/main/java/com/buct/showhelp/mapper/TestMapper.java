package com.buct.showhelp.mapper;

import java.util.List;

import com.buct.showhelp.pojo.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface TestMapper {

    @Select("select * from test ")
    List<Test> findAll();

}
