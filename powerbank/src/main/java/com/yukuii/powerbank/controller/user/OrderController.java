package com.yukuii.powerbank.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.yukuii.powerbank.service.user.OrderService;
import com.yukuii.powerbank.Model.DO.Order;
import com.yukuii.powerbank.Model.common.ApiResponse;

import java.util.List;

@RequestMapping("/user/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/complete/{userId}")
    public ApiResponse<List<Order>> getCompleteOrder(@PathVariable("userId") Integer userId) {
        return ApiResponse.success(orderService.getCompleteOrders(userId));
    }

    @GetMapping("/get/going/{userId}")
    public ApiResponse<Order> getGoingOrder(@PathVariable("userId") Integer userId) {
        return ApiResponse.success(orderService.getGoingOrders(userId));
    }

}
