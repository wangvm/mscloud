package com.example.springcloud;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author VM
 * @date 2022/2/9 22:54
 * @description
 */
public class ServletInitialzer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PaymentMain8006.class);
    }
}
