package com.buct.showhelp.service;

import com.buct.showhelp.pojo.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    int addOrder(Orders orders);

    int updateOrder(int id, String status, String content);

    List<Orders> findAllOrders();

    List<Orders> findOrdersByStatus(String status);

    Orders findOrdersById(int id);

    Orders findOrdersByStatusAndGoods(int id, String status);
}
