package com.example.cloudconsumerorder80.service;

import com.example.cloudApi.entity.Payment;
import com.example.cloudApi.utils.BackMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author VM
 * @date 2022/2/10 15:06
 * @description
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @PostMapping(value = "/payment/create")
    public BackMessage create(@RequestBody Payment payment);

    @GetMapping(value = "/payment/get/{id}")
    public BackMessage<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
