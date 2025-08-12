package com.spot.auction.aspot_auction.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BidRequest {
    private Long auctionItemSeq;
    private BigDecimal bidPrice;
    private Boolean isInstantBid;
}
