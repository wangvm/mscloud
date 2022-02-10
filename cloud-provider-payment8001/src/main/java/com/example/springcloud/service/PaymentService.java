package com.example.springcloud.service;

import com.example.cloudApi.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @create 2020-02-18 10:40
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
