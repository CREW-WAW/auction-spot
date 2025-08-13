package com.spot.auction.aspot_auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {
    private Long seq;
    private Long userSeq;
    private Long auctionItemSeq;
    private Integer bidPrice;
    private Boolean isInstantBid;
    private LocalDateTime createdAt;
}
