package com.spot.auction.aspot_auction.service;

import com.spot.auction.aspot_auction.dto.BidDto;
import com.spot.auction.aspot_auction.dto.BidRequest;
import com.spot.auction.aspot_auction.model.AuctionBidHistory;
import com.spot.auction.aspot_auction.repository.AuctionBidHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BidService {

    private final AuctionBidHistoryRepository auctionBidHistoryRepository;

    @Transactional
    public BidDto placeBid(Long userId, BidRequest request) {
        AuctionBidHistory bid = AuctionBidHistory.builder()
                .userSeq(userId)
                .auctionItemSeq(request.getAuctionItemSeq())
                .bidPrice(request.getBidPrice())
                .isInstantBid(request.getIsInstantBid())
                .build();

        AuctionBidHistory savedBid = auctionBidHistoryRepository.save(bid);
        return convertToDto(savedBid);
    }

    public List<BidDto> getMyBids(Long userId) {
        List<AuctionBidHistory> myBids = auctionBidHistoryRepository.findByUserSeq(userId);
        return myBids.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BidDto convertToDto(AuctionBidHistory bid) {
        return BidDto.builder()
                .seq(bid.getSeq())
                .userSeq(bid.getUserSeq())
                .auctionItemSeq(bid.getAuctionItemSeq())
                .bidPrice(bid.getBidPrice())
                .isInstantBid(bid.getIsInstantBid())
                .createdAt(bid.getCreatedAt())
                .build();
    }
}
