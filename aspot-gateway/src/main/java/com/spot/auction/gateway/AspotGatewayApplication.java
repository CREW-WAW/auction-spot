package com.spot.auction.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AspotGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AspotGatewayApplication.class, args);
    }
}
