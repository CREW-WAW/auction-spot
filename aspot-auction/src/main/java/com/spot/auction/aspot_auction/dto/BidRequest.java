package com.spot.auction.aspot_auction.dto;

import lombok.Data;

@Data
public class BidRequest {
    private Long auctionItemSeq;
    private Integer bidPrice;
    private Byte isInstantBid;
}
