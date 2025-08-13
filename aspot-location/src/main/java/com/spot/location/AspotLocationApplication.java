package com.spot.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.spot.location", "com.spot.auction.common"})
public class AspotLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspotLocationApplication.class, args);
	}

}
