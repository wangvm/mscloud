package com.example.cloudApi.cloudconsumerorder80.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author VM
 * @date 2022/2/10 12:06
 * @description
 */
@Slf4j
@Component
public class MyLB implements LoadBalancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        for (; ; ) {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if (this.atomicInteger.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int i = getAndIncrement() % serviceInstances.size();
        log.info("***第{}次请求", i);
        return serviceInstances.get(i);
    }
}
