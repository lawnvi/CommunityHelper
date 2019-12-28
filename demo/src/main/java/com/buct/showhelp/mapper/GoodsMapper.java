package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    //获取在售商品
    @Select("select * from goods where status = 'on_sale' ")
    List<Goods> findOnSaleGoods();

    //获取所有商品

    //获取某

}
