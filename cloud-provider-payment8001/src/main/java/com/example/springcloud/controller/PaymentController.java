package com.example.springcloud.controller;

import com.example.cloudApi.entity.Payment;
import com.example.cloudApi.utils.BackMessage;
import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author VM
 * @create 2020-02-18 10:43
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public BackMessage create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0) {
            return new BackMessage(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new BackMessage(444, "插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public BackMessage<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new BackMessage(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new BackMessage(444, "没有对应记录,查询ID: " + id, null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service: services) {
            log.info("***element:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info("*** instance id:{}, instance host:{}, instance port :{}",
                    instance.getInstanceId(), instance.getHost(), instance.getPort());
        }
        return discoveryClient;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
