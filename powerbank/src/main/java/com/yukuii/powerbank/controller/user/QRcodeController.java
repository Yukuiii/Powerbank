package com.yukuii.powerbank.controller.user;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.google.zxing.WriterException;
import com.yukuii.powerbank.Model.common.ApiResponse;
import com.yukuii.powerbank.service.user.DeviceService;
import com.yukuii.powerbank.utils.QRCodeGenerator;

@Controller
@RequestMapping("/user/qrcode")
public class QRcodeController {

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/get")
    public ResponseEntity<byte[]> getQRCode(@RequestParam("url") String url) {

        System.out.println(url);
        try {
            byte[] qrCodeImage = qrCodeGenerator.getQRCode(url, 250, 250);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCodeImage, headers, HttpStatus.OK);
        } catch (WriterException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rent")
    public RedirectView rentDevice(@RequestParam("userId") Integer userId,
            @RequestParam("deviceId") String deviceId) {
        deviceService.rentDevice(deviceId, userId);
        //这里换成前端成功页面的地址
        return new RedirectView("https://powerbank.yukuii.cn/user/rentsuccess");
    }

    @ResponseBody
    @GetMapping("/isScan")
    public ApiResponse<String> isScan(@RequestParam String deviceId) {
        if (deviceService.isScan(deviceId)) {
            return ApiResponse.success("设备已扫码");
        } else {
            return ApiResponse.error("设备未扫码");
        }
    }

}
