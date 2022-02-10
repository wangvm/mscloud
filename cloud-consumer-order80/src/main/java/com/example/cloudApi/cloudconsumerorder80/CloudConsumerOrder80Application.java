package com.example.cloudApi.cloudconsumerorder80;

import com.example.rule.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author VM
 */
@EnableEurekaClient
@SpringBootApplication
// @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = RibbonConfig.class)
public class CloudConsumerOrder80Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrder80Application.class, args);
    }

}
