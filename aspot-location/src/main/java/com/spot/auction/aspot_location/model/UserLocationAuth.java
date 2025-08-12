package com.spot.auction.aspot_location.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLocationAuth {
    private Long seq;
    private Long userSeq;
    private Long locationSeq;
    private AuthStatus authStatus;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime expiredAt;
    private Boolean isActive;
    private LocalDateTime createdAt;

    public enum AuthStatus {
        PENDING, APPROVED, REJECTED, EXPIRED
    }
}
