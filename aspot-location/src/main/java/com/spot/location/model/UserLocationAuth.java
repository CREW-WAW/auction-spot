package com.spot.location.model;

import com.spot.auction.common.generated.enums.TbUserLocationAuthAuthStatus;
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
    private TbUserLocationAuthAuthStatus authStatus;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private LocalDateTime expiredAt;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
