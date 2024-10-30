package com.yukuii.powerbank.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.service.admin.AdminOrderService;
import com.yukuii.powerbank.Model.DO.Order;





@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;


    @GetMapping("/get")
    public ApiResponse<List<Order>> getOrders() {
        return ApiResponse.success(adminOrderService.getOrders());
    }

    @GetMapping("/getById/{orderId}")
    public ApiResponse<Order> getOrderById(@PathVariable String orderId) {
        return ApiResponse.success(adminOrderService.getOrderById(orderId));
    }

    @PostMapping("/save")
    public ApiResponse<Integer> saveOrder(@RequestBody Order order) {
        System.out.println(order);
        return ApiResponse.success(adminOrderService.saveOrder(order));
    }

    @PostMapping("/update")
    public ApiResponse<Integer> updateOrder(@RequestBody Order order) {
        return ApiResponse.success(adminOrderService.updateOrder(order));
    }

    @GetMapping("/delete/{orderId}")
    public ApiResponse<Integer> deleteOrder(@PathVariable String orderId) {
        return ApiResponse.success(adminOrderService.deleteOrder(orderId));
    }

}
