package com.yukuii.powerbank.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.powerbank.Model.DO.User;
import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 



@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        User user = userService.login(username, password);
        if (user != null) {
            return ApiResponse.success(user);
        } else {
            return ApiResponse.error("用户名或密码错误");
        }
    }

    @PostMapping("/recharge")
    public ApiResponse<User> recharge(@RequestBody Map<String, String> params) {
        Integer userId = Integer.parseInt(params.get("userId"));
        Double amount = Double.parseDouble(params.get("amount"));
        userService.addBalance(userId, amount);
        return ApiResponse.success(null);
    }

    @GetMapping("/get/{userId}")
    public ApiResponse<User> getUser(@PathVariable("userId") Integer userId) {
        User user = userService.getUserById(userId);
        return ApiResponse.success(user);
    }
    
    @PostMapping("/update")
    public ApiResponse<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ApiResponse.success(null);
    }

    
}
