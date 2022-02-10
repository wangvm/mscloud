package com.example.cloudApi.cloudconsumerorder80.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author VM
 * @date 2022/2/10 12:04
 * @description
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
