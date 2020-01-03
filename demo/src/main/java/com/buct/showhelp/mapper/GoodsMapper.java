package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Goods;
import com.buct.showhelp.pojo.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsMapper {
    //获取在售商品
    @Select("select * from goods where status = 'on_sale' ")
    List<Goods> findOnSaleGoods();

    //获取所有商品

    //获取我的在售
    @Select("select * from goods where status = 'on_sale' and sellerid = #{sellerid}")
    List<Goods> findMyOnSaleGoods(int sellerid);

//  title detail price purchaseUrl location number
    //上架
    @Insert("insert into goods (title,detail,price,purchaseUrl,location,number,sellerid) values (#{title}, #{detail}, #{price}, #{url}, #{location}, #{number}, #{sellerid})")
    int addGoods(String title, String detail, double price, String url, String location, int number, int sellerid);
//    int addGoods(Users users);

    //更新物品
    @Update("update goods set title = #{title}, detail = #{detail}, price = #{price}, purchaseUrl = #{purchaseUrl}, location = #{location}, number = #{number} where id = #{id}")
    int updateGoods(Goods goods);

    // 更新喜欢、访问、状态
    @Update("update Goods set like = #{like}, visit = #{visit}, status = #{status} where id = #{id}")
    int updateGoodsStatus(Goods goods);

    //获取一个物品
    @Select("select * from goods where id = #{id}")
    Goods findGoodsById(int id);

}
