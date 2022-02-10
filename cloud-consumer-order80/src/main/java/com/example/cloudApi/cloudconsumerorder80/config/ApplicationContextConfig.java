package com.example.cloudApi.cloudconsumerorder80.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author VM
 * @date 2022/2/7 20:42
 * @description
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    // @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
