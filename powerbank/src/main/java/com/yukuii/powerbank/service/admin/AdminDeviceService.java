package com.yukuii.powerbank.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yukuii.powerbank.Model.DO.Device;
import com.yukuii.powerbank.mapper.DeviceMapper;

@Service("AdminDeviceService")
public class AdminDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public List<Device> getDevices() {
        return deviceMapper.selectList(null);
    }

    public Device getDeviceById(String deviceId) {
        return deviceMapper.selectById(deviceId);
    }

    public Integer saveDevice(Device device) {
        return deviceMapper.insert(device);
    }

    public Integer updateDevice(Device device) {
        return deviceMapper.updateById(device);
    }

    public Integer deleteDevice(String deviceId) {
        return deviceMapper.deleteById(deviceId);
    }

}
