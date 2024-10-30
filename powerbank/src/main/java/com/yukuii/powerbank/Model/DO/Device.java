package com.yukuii.powerbank.Model.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("devices")
public class Device {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("device_id")
    private String deviceId;

    @TableField("status")
    private Boolean status;

    @TableField("hourly_rate")
    private Double hourlyRate;

    @TableField("location")
    private String location;

    @TableField("imgsrc")
    private String imgsrc;
    
    @TableField("distance")
    private String distance;

}
