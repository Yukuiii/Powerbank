package com.yukuii.powerbank.service.user;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.yukuii.powerbank.Model.Enum.OrderStatus;
import java.text.ParseException;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yukuii.powerbank.Model.DO.Order;
import java.util.List;
import java.util.Collections;
import com.yukuii.powerbank.mapper.OrderMapper;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    public Order createOrder(String deviceId, Integer userId) {
        Order order = new Order();
        order.setOrderNumber(generateUniqueOrderNumber());
        order.setUserId(userId);
        order.setDeviceId(deviceId);
        order.setStatus(OrderStatus.IS_GOING.toString());
        order.setStartTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setEndTime(null);
        order.setScanQRCode(false);
        order.setAmount(0.00);
        orderMapper.insert(order);
        return order;
    }

    public Order completeOrder(String orderNumber, double hourlyRate) throws ParseException {
        Order order = orderMapper.selectOne(new QueryWrapper<Order>().eq("order_number", orderNumber));
        order.setStatus(OrderStatus.IS_COMPLETE.toString());
        double amount = calculateOrderAmount(order.getStartTime(), hourlyRate);
        order.setAmount(amount);
        userService.reduceBalance(order.getUserId(), amount);
        order.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        orderMapper.update(order, new UpdateWrapper<Order>().eq("order_number", orderNumber));
        return order;
    }

    public static String generateUniqueOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return timestamp + uuid;
    }

    public double calculateOrderAmount(String starttime, double hourly_rate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = dateFormat.parse(starttime);
        Date date2 = new Date();
        long differenceInMillis = date2.getTime() - date1.getTime();
        long differenceInHours = differenceInMillis / 1000;
        return differenceInHours * hourly_rate;
    }

    public List<Order> getCompleteOrders(Integer userId) {
        List<Order> orders = orderMapper.selectList(
                new QueryWrapper<Order>().eq("user_id", userId).eq("status", OrderStatus.IS_COMPLETE.toString()));
        Collections.sort(orders, (o1, o2) -> {
            return o2.getEndTime().compareTo(o1.getEndTime());
        });
        return orders;
    }

    public Order getGoingOrders(Integer userId) {
        Order order = orderMapper.selectOne(
                new QueryWrapper<Order>().eq("user_id", userId).eq("status", OrderStatus.IS_GOING.toString()));
        return order;
    }

    public Order getOrderIsGoingByDeviceId(String deviceId) {
        return orderMapper.selectOne(
                new QueryWrapper<Order>().eq("device_id", deviceId).eq("status", OrderStatus.IS_GOING.toString()));
    }

}
