package com.spot.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.spot.notification", "com.spot.auction.common"})
public class AspotNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspotNotificationApplication.class, args);
	}

}
