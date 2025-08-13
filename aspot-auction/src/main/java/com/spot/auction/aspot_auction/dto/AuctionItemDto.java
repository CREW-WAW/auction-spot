package com.spot.auction.aspot_auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionItemDto {
    private Long seq;
    private Long userSeq;
    private String title;
    private String description;
    private String imageUrl;
    private Integer startPrice;
    private String category;
    private LocalDate auctionDate;
    private String status;
    private Integer sortOrder;
    private LocalDateTime createdAt;
}
