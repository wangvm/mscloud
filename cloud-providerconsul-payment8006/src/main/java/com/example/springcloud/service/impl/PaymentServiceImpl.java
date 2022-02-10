package com.example.springcloud.service.impl;

import com.example.cloudApi.entity.Payment;
import com.example.springcloud.dao.PaymentDao;
import com.example.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author VM
 * @create 2020-02-18 10:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
