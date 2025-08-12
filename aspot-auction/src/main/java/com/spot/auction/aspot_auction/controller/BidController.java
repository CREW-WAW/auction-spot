package com.spot.auction.aspot_auction.controller;

import com.spot.auction.aspot_auction.dto.BidDto;
import com.spot.auction.aspot_auction.dto.BidRequest;
import com.spot.auction.aspot_auction.service.BidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidService bidService;

    @PostMapping
    public ResponseEntity<BidDto> placeBid(@RequestBody BidRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        BidDto bid = bidService.placeBid(userId, request);
        return ResponseEntity.ok(bid);
    }

    @GetMapping("/my")
    public ResponseEntity<List<BidDto>> getMyBids() {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        List<BidDto> myBids = bidService.getMyBids(userId);
        return ResponseEntity.ok(myBids);
    }
}
