package com.example.rule;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author VM
 * @date 2022/2/10 11:46
 * @description
 */
@Configuration
public class RibbonConfig {
    @Bean
    public IRule setLoadBalanceRule() {
        return new RandomRule();
    }
}
