package com.spot.auction.aspot_auction.controller;

import com.spot.auction.aspot_auction.dto.AuctionItemDto;
import com.spot.auction.aspot_auction.dto.AuctionItemRequest;
import com.spot.auction.aspot_auction.service.AuctionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class AuctionItemController {

    private final AuctionItemService auctionItemService;

    @PostMapping
    public ResponseEntity<AuctionItemDto> createAuctionItem(@RequestBody AuctionItemRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        AuctionItemDto createdItem = auctionItemService.createAuctionItem(userId, request);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping
    public ResponseEntity<List<AuctionItemDto>> getAllAuctionItems() {
        List<AuctionItemDto> items = auctionItemService.getAllAuctionItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionItemDto> getAuctionItemById(@PathVariable Long id) {
        AuctionItemDto item = auctionItemService.getAuctionItemById(id);
        return ResponseEntity.ok(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuctionItemDto> updateAuctionItem(@PathVariable Long id, @RequestBody AuctionItemRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        AuctionItemDto updatedItem = auctionItemService.updateAuctionItem(id, userId, request);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuctionItem(@PathVariable Long id) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        auctionItemService.deleteAuctionItem(id, userId);
        return ResponseEntity.noContent().build();
    }
}
