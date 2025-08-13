package com.spot.auction.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        
        // Public endpoints that don't require authentication
        if (isPublicEndpoint(path)) {
            return chain.filter(exchange);
        }
        
        String token = getJwtFromRequest(request);
        
        if (StringUtils.hasText(token) && validateToken(token)) {
            // Add user info to headers for downstream services
            ServerHttpRequest modifiedRequest = request.mutate()
                    .header("X-User-Id", extractUserIdFromToken(token))
                    .build();
            
            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
    
    private boolean isPublicEndpoint(String path) {
        return path.startsWith("/api/auth/") || 
               path.equals("/api/users/signup") || 
               path.equals("/api/users/login") ||
               path.startsWith("/actuator/");
    }
    
    private String getJwtFromRequest(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    
    private boolean validateToken(String token) {
        // TODO: Implement actual JWT validation logic
        // For now, just check if token exists and has basic format
        return StringUtils.hasText(token) && token.length() > 10;
    }
    
    private String extractUserIdFromToken(String token) {
        // TODO: Implement actual JWT parsing logic
        // For now, return a placeholder
        return "user-123";
    }

    @Override
    public int getOrder() {
        return -50; // After rate limiting, before logging
    }
}
