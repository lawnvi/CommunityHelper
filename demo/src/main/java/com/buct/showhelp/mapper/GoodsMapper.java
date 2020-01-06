package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {
    /**
     * asUser
     */
    //获取在售商品
    @Select("select * from goods where status = 'on_sale' ")
    List<Goods> findOnSaleGoods();

    //获取所有商品
    @Select("select * from goods")
    List<Goods> findAllGoods();

    /**
     * asSeller
     */
    //按物品状态获取
    @Select("select * from goods where status = #{status} and sellerid = #{sellerid}")
    List<Goods> findGoodsByStatus(int sellerid, String status);

//  title detail price purchaseUrl location number
    //上架
    @Insert("insert into goods (title,detail,price,purchaseUrl,location,number,sellerid) values (#{title}, #{detail}, #{price}, #{url}, #{location}, #{number}, #{sellerid})")
    int addGoods(String title, String detail, double price, String url, String location, int number, int sellerid);
//    int addGoods(Users users);

    //更新物品
    @Update("update goods set title = #{title}, detail = #{detail}, price = #{price}, purchaseUrl = #{purchaseUrl}, location = #{location}, number = #{number} where id = #{id}")
    int updateGoods(Goods goods);

    // 更新喜欢、访问、状态
    @Update("update goods set buyerid = #{buyerid}, star = #{star}, visit = #{visit}, status = #{status} where id = #{id}")
    int updateGoodsStatus(Goods goods);

    //delete a goods
    @Delete("delete from goods where id = #{id}")
    int deleteGoods(int id);

    //获取一个物品
    @Select("select * from goods where id = #{id}")
    Goods findGoodsById(int id);

    /**
     * asBuyer
     */
    //购买
    @Update("update goods set buyerid = #{buyerid}, selltime = #{time}, status = #{status} where id = #{goodsid}")
    int buyGoods(int buyerid, int goodsid, String time, String status);

    //获取我的订单
    @Select("select * from goods where status = #{status} and buyerid = #{buyerid}")
    List<Goods> findGoodsByBuyerId(int buyerid, String status);

    //模糊搜索
    @Select("select * from goods where detail like _#{keyword}_ or title like _#{keyword}_")
    List<Goods> findGoodsByKeyword(String keyword);

    //按价格搜索
    @Select("select * from goods where price >= #{priceLow} and price <= #{priceHigh}")
    List<Goods> findGoodsByPrice(float priceLow, float priceHigh);
}
