package com.example.cloudconsumerorder80.controller;

import com.example.cloudApi.entity.Payment;
import com.example.cloudApi.utils.BackMessage;
import com.example.cloudconsumerorder80.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author VM
 * @date 2022/2/7 20:40
 * @description
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public BackMessage<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("***获取指定id：{}订单", id);
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon，默认等待时间为1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
