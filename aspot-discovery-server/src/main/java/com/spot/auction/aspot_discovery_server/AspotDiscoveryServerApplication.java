package com.spot.auction.aspot_discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AspotDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspotDiscoveryServerApplication.class, args);
	}

}
