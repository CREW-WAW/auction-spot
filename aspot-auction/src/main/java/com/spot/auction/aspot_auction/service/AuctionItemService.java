package com.spot.auction.aspot_auction.service;

import com.spot.auction.aspot_auction.dto.AuctionItemDto;
import com.spot.auction.aspot_auction.dto.AuctionItemRequest;
import com.spot.auction.aspot_auction.model.AuctionItem;
import com.spot.auction.aspot_auction.repository.AuctionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuctionItemService {

    private final AuctionItemRepository auctionItemRepository;

    @Transactional
    public AuctionItemDto createAuctionItem(Long userId, AuctionItemRequest request) {
        AuctionItem auctionItem = AuctionItem.builder()
                .userSeq(userId)
                .title(request.getTitle())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .startPrice(request.getStartPrice())
                .category(request.getCategory())
                .auctionDate(request.getAuctionDate())
                .status(request.getStatus())
                .sortOrder(request.getSortOrder())
                .build();

        AuctionItem savedItem = auctionItemRepository.save(auctionItem);
        return convertToDto(savedItem);
    }

    public List<AuctionItemDto> getAllAuctionItems() {
        List<AuctionItem> items = auctionItemRepository.findAll();
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AuctionItemDto getAuctionItemById(Long id) {
        AuctionItem item = auctionItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction item not found"));
        return convertToDto(item);
    }

    @Transactional
    public AuctionItemDto updateAuctionItem(Long id, Long userId, AuctionItemRequest request) {
        AuctionItem item = auctionItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction item not found"));

        if (!item.getUserSeq().equals(userId)) {
            throw new RuntimeException("Not authorized to update this item");
        }

        item.setTitle(request.getTitle());
        item.setDescription(request.getDescription());
        item.setImageUrl(request.getImageUrl());
        item.setStartPrice(request.getStartPrice());
        item.setCategory(request.getCategory());
        item.setAuctionDate(request.getAuctionDate());
        item.setStatus(request.getStatus());
        item.setSortOrder(request.getSortOrder());

        AuctionItem savedItem = auctionItemRepository.save(item);
        return convertToDto(savedItem);
    }

    @Transactional
    public void deleteAuctionItem(Long id, Long userId) {
        AuctionItem item = auctionItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction item not found"));

        if (!item.getUserSeq().equals(userId)) {
            throw new RuntimeException("Not authorized to delete this item");
        }

        auctionItemRepository.deleteById(id);
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
