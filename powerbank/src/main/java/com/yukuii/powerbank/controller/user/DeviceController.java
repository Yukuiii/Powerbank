package com.yukuii.powerbank.controller.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yukuii.powerbank.Model.DO.Device;
import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.service.user.DeviceService;




@RequestMapping("/user/device")
@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/get")
    public List<Device> getDevices() {
        return deviceService.getDevices();
    }

    @GetMapping("/rent")
    public ApiResponse<JSONObject> rentDevice(@RequestParam("userId") Integer userId,
            @RequestParam("deviceId") String deviceId) {
        deviceService.rentDevice(deviceId, userId);
        return ApiResponse.success(null);
    }

    @PostMapping("/retuen")
    public ApiResponse<JSONObject> returnDevice(@RequestBody Map<String, String> params) {
        String orderNumber = params.get("orderNumber");
        String deviceId = params.get("deviceId");
        deviceService.returnDevice(orderNumber, deviceId);
        return ApiResponse.success(null);
    }

}
