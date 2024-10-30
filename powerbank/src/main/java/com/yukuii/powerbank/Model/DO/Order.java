package com.yukuii.powerbank.Model.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import lombok.Data;

@Data
@TableName("orders")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer orderId;

    @TableField("order_number")
    private String orderNumber;

    @TableField("user_id")
    private Integer userId;

    @TableField("device_id")
    private String deviceId;

    @TableField("status")
    private String status;

    @TableField("start_time")
    private String startTime;

    @TableField("end_time")
    private String endTime;
    
    @TableField("amount")
    private double amount;

    @TableField("isscanqrcode")
    private boolean isScanQRCode;

}
