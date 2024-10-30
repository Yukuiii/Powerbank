package com.yukuii.powerbank.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yukuii.powerbank.Model.DO.Order;
import com.yukuii.powerbank.mapper.OrderMapper;

@Service("AdminOrderService")
public class AdminOrderService {

    @Autowired
    private OrderMapper orderMapper;


    public List<Order> getOrders() {
        return orderMapper.selectList(null);
    }

    public Order getOrderById(String orderId) {
        return orderMapper.selectById(orderId);
    }

    public Integer saveOrder(Order order) {
        return orderMapper.insert(order);
    }

    public Integer updateOrder(Order order) {
        return orderMapper.updateById(order);
    }

    public Integer deleteOrder(String orderId) {
        return orderMapper.deleteById(orderId);
    }
}
