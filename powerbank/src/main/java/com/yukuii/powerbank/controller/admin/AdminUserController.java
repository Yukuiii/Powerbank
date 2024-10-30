package com.yukuii.powerbank.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.Model.DO.Admin;
import com.yukuii.powerbank.Model.DO.User;
import java.util.List;
import java.util.Map;

import com.yukuii.powerbank.service.admin.AdminService;
import com.yukuii.powerbank.service.admin.AdminUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private AdminUserService userService;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ApiResponse<Admin> login(@RequestBody Map<String, String> admin) {
        Admin Newadmin = adminService.login(admin.get("username"), admin.get("password"));
        if (Newadmin != null) {
            return ApiResponse.success(Newadmin);
        } else {
            return ApiResponse.error("用户名或密码错误");
        }
    }

    @GetMapping("/get")
    public ApiResponse<List<User>> getUsers() {
        return ApiResponse.success(userService.getUsers());
    }

    @GetMapping("/getById/{userId}")
    public ApiResponse<User> getUserById(@PathVariable String userId) {
        return ApiResponse.success(userService.getUserById(userId));
    }

    @PostMapping("/save")
    public ApiResponse<Integer> saveUser(@RequestBody User user) {
        System.out.println(user);
        return ApiResponse.success(userService.saveUser(user));
    }

    @PostMapping("/update")
    public ApiResponse<Integer> updateUser(@RequestBody User user) {
        return ApiResponse.success(userService.updateUser(user));
    }

    @GetMapping("/delete/{userId}")
    public ApiResponse<Integer> deleteUser(@PathVariable String userId) {
        return ApiResponse.success(userService.deleteUser(userId));
    }

}
