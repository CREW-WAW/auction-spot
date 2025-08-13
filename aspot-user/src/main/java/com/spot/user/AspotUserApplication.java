package com.spot.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.spot.user", "com.spot.auction.common"})
public class AspotUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspotUserApplication.class, args);
	}

}
