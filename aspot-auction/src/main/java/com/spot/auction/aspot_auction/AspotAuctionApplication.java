package com.spot.auction.aspot_auction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AspotAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AspotAuctionApplication.class, args);
	}

}
