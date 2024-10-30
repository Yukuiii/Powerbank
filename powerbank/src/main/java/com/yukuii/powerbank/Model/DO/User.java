package com.yukuii.powerbank.Model.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
@TableName("users")
@Data
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("email")
   private String email;

    @TableField("balance")
   private double balance;

    @TableField("registration_time")
    private String registrationTime;

    @TableField("avatarimgsrc")
    private String avatarimgsrc;
}
