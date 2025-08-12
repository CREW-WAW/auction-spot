package com.spot.auction.aspot_auction.controller;

import com.spot.auction.aspot_auction.dto.AuctionItemDto;
import com.spot.auction.aspot_auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionService auctionService;

    @GetMapping("/today")
    public ResponseEntity<List<AuctionItemDto>> getTodayAuctions() {
        List<AuctionItemDto> todayAuctions = auctionService.getTodayAuctions();
        return ResponseEntity.ok(todayAuctions);
    }

    @GetMapping("/live")
    public ResponseEntity<List<AuctionItemDto>> getLiveAuctions() {
        List<AuctionItemDto> liveAuctions = auctionService.getLiveAuctions();
        return ResponseEntity.ok(liveAuctions);
    }

    @GetMapping("/result")
    public ResponseEntity<List<AuctionItemDto>> getAuctionResults() {
        List<AuctionItemDto> results = auctionService.getAuctionResults();
        return ResponseEntity.ok(results);
    }
}
