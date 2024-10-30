package com.yukuii.powerbank.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yukuii.powerbank.Model.DO.User;
import com.yukuii.powerbank.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public void reduceBalance(Integer userId, Double amount) {
        User user = userMapper.selectById(userId);
        user.setBalance(user.getBalance() - amount);
        userMapper.updateById(user);
    }

    public void addBalance(Integer userId, Double amount) {
        User user = userMapper.selectById(userId);
        user.setBalance(user.getBalance() + amount);
        userMapper.updateById(user);
    }

    public User getUserById(Integer userId) {
        return userMapper.selectById(userId);
    }

    public void updateUser(User user) {
        userMapper.updateById(user);
    }

}
