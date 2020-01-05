package com.buct.showhelp.service.impl;

import com.buct.showhelp.mapper.OrdersMapper;
import com.buct.showhelp.pojo.Orders;
import com.buct.showhelp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public int addOrder(Orders orders) {
        return ordersMapper.addOrder(orders);
    }

    @Override
    public int updateOrder(int id, String status, String content) {
        return ordersMapper.updateOrder(id, status, content);
    }

    @Override
    public List<Orders> findAllOrders() {
        return ordersMapper.findAllOrders();
    }

    @Override
    public List<Orders> findOrdersByStatus(String status) {
        return ordersMapper.findOrdersByStatus(status);
    }

    @Override
    public Orders findOrdersById(int id) {
        return ordersMapper.findOrdersById(id);
    }

    @Override
    public Orders findOrdersByStatusAndGoods(int id, String status){
        return ordersMapper.findOrdersByStatusAndGoods(id, status);
    }
}
