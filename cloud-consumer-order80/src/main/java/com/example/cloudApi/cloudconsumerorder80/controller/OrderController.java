package com.example.cloudApi.cloudconsumerorder80.controller;

import com.example.cloudApi.cloudconsumerorder80.lb.LoadBalancer;
import com.example.cloudApi.utils.BackMessage;
import com.example.cloudApi.entity.Payment;
import com.sun.jndi.toolkit.url.Uri;
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
    public static final String PAYMEN_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public BackMessage create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMEN_URL + "/payment/create", payment, BackMessage.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public BackMessage getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMEN_URL + "/payment/get/" + id, BackMessage.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public BackMessage getPaymentInfo(@PathVariable("id") Long id) {
        ResponseEntity<BackMessage> entity = restTemplate.getForEntity(PAYMEN_URL + "/payment/get/" + id, BackMessage.class);
        log.info("***status code:{}", entity.getStatusCode());
        log.info("***body:{}", entity.getBody());
        return entity.getBody();
    }

    @GetMapping("/consumer/payment/lb/{id}")
    public BackMessage getPaymentLB(@PathVariable("id") Long id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/get/" + id, BackMessage.class);
    }
}
