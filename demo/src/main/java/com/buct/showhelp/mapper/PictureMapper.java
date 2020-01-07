package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.pojo.GoodsPicPath;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 物品图片存储
 */
@Mapper
public interface PictureMapper {
    @Insert("insert into goodspic (goodsid, picturePath) values (#{goodsid}, #{picturePath})")
    int saveGoodsPicPath(GoodsPicPath path);

    @Select("select * from goodspic where id = #{id}")
    GoodsPicPath getGoodsPicPathById(int id);

    @Select("select * from goodspic where goodsid = #{id}")
    List<GoodsPicPath> getGoodsPicPathByGoods(int id);

    @Update("update goodspic set picturePath = #{picturePath} where id = #{id}")
    int updateGoodsPicPath(GoodsPicPath path);

    @Delete("delete goodspic where id = #{id}")
    int deleteGoodsPicPathById(int id);

    @Delete("delete goodspic where goodsid = #{id}")
    int deleteGoodsPicPathByGoods(int id);
}
