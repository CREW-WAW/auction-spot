package com.spot.auction.aspot_auction.service;

import com.spot.auction.aspot_auction.dto.AuctionItemDto;
import com.spot.auction.aspot_auction.model.AuctionItem;
import com.spot.auction.aspot_auction.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionItemRepository auctionItemRepository;

    public List<AuctionItemDto> getTodayAuctions() {
        LocalDate today = LocalDate.now();
        List<AuctionItem> todayItems = auctionItemRepository.findByAuctionDate(today);
        return todayItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<AuctionItemDto> getLiveAuctions() {
        LocalDate today = LocalDate.now();
        List<AuctionItem> liveItems = auctionItemRepository.findByAuctionDateAndStatus(today, "LIVE");
        return liveItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<AuctionItemDto> getAuctionResults() {
        List<AuctionItem> completedItems = auctionItemRepository.findByStatus("COMPLETED");
        return completedItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AuctionItemDto convertToDto(AuctionItem item) {
        return AuctionItemDto.builder()
                .seq(item.getSeq())
                .userSeq(item.getUserSeq())
                .title(item.getTitle())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .startPrice(item.getStartPrice())
                .category(item.getCategory())
                .auctionDate(item.getAuctionDate())
                .status(item.getStatus())
                .sortOrder(item.getSortOrder())
                .createdAt(item.getCreatedAt())
                .build();
    }
}
