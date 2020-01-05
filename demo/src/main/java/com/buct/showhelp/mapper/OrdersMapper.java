package com.buct.showhelp.mapper;

import com.buct.showhelp.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrdersMapper {
    //添加订单 waiting
    @Insert("insert into orders (sellerid, buyerid, goodsid, price, content) values (#{sellerId}, #{buyerId}, #{goodsId}, #{price}, #{content})")
    int addOrder(Orders orders);

    //buyer cancel -> status:buyerNo  seller refuse -> status:sellerNo /success
    @Update("update orders set status = #{status}, content = #{content} where id = #{id}")
    int updateOrder(int id, String status, String content);

    //查看交易表
    @Select("select * from orders")
    List<Orders> findAllOrders();

    @Select("select * from orders where status = #{status}")
    List<Orders> findOrdersByStatus(String status);

    @Select("select * from orders where id = #{id}")
    Orders findOrdersById(int id);

    @Select("select * from orders where goodsid = #{goodsid} and status = #{status}")
    Orders findOrdersByStatusAndGoods(int goodsid, String status);
}
