package com.yukuii.powerbank.service.admin;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yukuii.powerbank.mapper.UserMapper;
import com.yukuii.powerbank.Model.DO.User;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service("AdminUserService")
public class AdminUserService {

    @Autowired
    private UserMapper userMapper;
    
    //登录
    public User login(String username, String password) {
      User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
      if(user == null){
        return null;
      }
      if(user.getPassword().equals(password)){
        return user;
      }else{
        return null;
      }
    }

    //获取所有用户
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    
    public User getUserById (String userId){
      return userMapper.selectById(userId);
    }

    public Integer saveUser (User user){
      return userMapper.insert(user);
    }

    public Integer updateUser(User user){
      return userMapper.updateById(user);
    }

    public Integer deleteUser(String userId){
      return userMapper.deleteById(userId);
    }
}
