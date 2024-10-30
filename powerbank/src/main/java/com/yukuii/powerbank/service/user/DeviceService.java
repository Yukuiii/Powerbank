package com.yukuii.powerbank.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yukuii.powerbank.Model.DO.Device;
import com.yukuii.powerbank.Model.DO.Order;
import com.yukuii.powerbank.mapper.DeviceMapper;

@Service
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private OrderService orderService;

    public List<Device> getDevices() {
        return deviceMapper.selectList(new QueryWrapper<Device>().eq("status", false));
    }

    public Device getDeviceById(String deviceId) {
        return deviceMapper.selectOne(new QueryWrapper<Device>().eq("device_id", deviceId));
    }

    public Integer changeDeviceStatus(String deviceId, boolean param) {
        Device device = deviceMapper.selectOne(new QueryWrapper<Device>().eq("device_id", deviceId));
        device.setStatus(param);
        return deviceMapper.update(device, new UpdateWrapper<Device>().eq("device_id", deviceId));
    }

    public void rentDevice(String deviceId, Integer userId) {
        try {
            orderService.createOrder(deviceId, userId);
            changeDeviceStatus(deviceId, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("租借设备时发生错误", e);
        }
    }

    public void returnDevice(String orderNumber, String deviceId) {
        try {
            orderService.completeOrder(orderNumber, getDeviceById(deviceId).getHourlyRate());
            changeDeviceStatus(deviceId, false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("归还设备时发生错误", e);
        }
    }

    public boolean isScan(String deviceId) {
        Order order = orderService.getOrderIsGoingByDeviceId(deviceId);
        if (order == null) {
            return false;
        } else {
            return true;
        }
    }

}
