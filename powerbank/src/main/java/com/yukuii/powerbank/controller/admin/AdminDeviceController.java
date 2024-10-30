package com.yukuii.powerbank.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.service.admin.AdminDeviceService;
import com.yukuii.powerbank.Model.DO.Device;

@RestController
@RequestMapping("/admin/device")
public class AdminDeviceController {

    @Autowired
    private AdminDeviceService adminDeviceService;

    @GetMapping("/get")
    public ApiResponse<List<Device>> getDevices() {
        return ApiResponse.success(adminDeviceService.getDevices());
    } 

    @GetMapping("/getById/{deviceId}")
    public ApiResponse<Device> getDeviceById(@PathVariable String deviceId) {
        return ApiResponse.success(adminDeviceService.getDeviceById(deviceId));
    }

    @PostMapping("/save")
    public ApiResponse<Integer> saveDevice(@RequestBody Device device) {
        return ApiResponse.success(adminDeviceService.saveDevice(device));
    }

    @PostMapping("/update")
    public ApiResponse<Integer> updateDevice(@RequestBody Device device) {
        return ApiResponse.success(adminDeviceService.updateDevice(device));
    }

    @GetMapping("/delete/{deviceId}")
    public ApiResponse<Integer> deleteDevice(@PathVariable String deviceId) {
        return ApiResponse.success(adminDeviceService.deleteDevice(deviceId));
    }
}
