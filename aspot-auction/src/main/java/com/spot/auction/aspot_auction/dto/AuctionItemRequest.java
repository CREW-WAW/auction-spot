package com.spot.auction.aspot_auction.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuctionItemRequest {
    private String title;
    private String description;
    private String imageUrl;
    private Integer startPrice;
    private String category;
    private LocalDate auctionDate;
    private String status;
    private Integer sortOrder;
}
