package com.yukuii.powerbank.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yukuii.powerbank.Model.DO.Admin;
import com.yukuii.powerbank.mapper.AdminMapper;

@Service("AdminService")
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public Admin login(String username, String password) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
        if (admin == null) {
            return null;
        }
        if (admin.getPassword().equals(password)) {
            return admin;
        } else {
            return null;
        }
    }

}
