package com.spot.auction.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/user-service")
    public Mono<ResponseEntity<Map<String, Object>>> userServiceFallback() {
        return createFallbackResponse("User Service", "Service temporarily unavailable");
    }

    @GetMapping("/auction-service")
    public Mono<ResponseEntity<Map<String, Object>>> auctionServiceFallback() {
        return createFallbackResponse("Auction Service", "Service temporarily unavailable");
    }

    @GetMapping("/location-service")
    public Mono<ResponseEntity<Map<String, Object>>> locationServiceFallback() {
        return createFallbackResponse("Location Service", "Service temporarily unavailable");
    }

    @GetMapping("/notification-service")
    public Mono<ResponseEntity<Map<String, Object>>> notificationServiceFallback() {
        return createFallbackResponse("Notification Service", "Service temporarily unavailable");
    }

    private Mono<ResponseEntity<Map<String, Object>>> createFallbackResponse(String serviceName, String message) {
        Map<String, Object> fallbackInfo = new HashMap<>();
        fallbackInfo.put("service", serviceName);
        fallbackInfo.put("status", "FALLBACK");
        fallbackInfo.put("message", message);
        fallbackInfo.put("timestamp", LocalDateTime.now().format(formatter));
        fallbackInfo.put("circuitBreaker", "OPEN");
        
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackInfo));
    }
}
