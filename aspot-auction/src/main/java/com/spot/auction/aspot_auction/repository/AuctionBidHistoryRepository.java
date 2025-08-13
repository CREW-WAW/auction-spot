package com.spot.auction.aspot_auction.repository;

import com.spot.auction.aspot_auction.model.AuctionBidHistory;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spot.auction.common.generated.Tables.TB_AUCTION_BID_HISTORY;

@Repository
public class AuctionBidHistoryRepository {

    private final DSLContext dslContext;

    public AuctionBidHistoryRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<AuctionBidHistory> findByUserSeq(Long userSeq) {
        return dslContext.selectFrom(TB_AUCTION_BID_HISTORY)
                .where(TB_AUCTION_BID_HISTORY.USER_SEQ.eq(userSeq))
                .orderBy(TB_AUCTION_BID_HISTORY.CREATED_AT.desc())
                .fetchInto(AuctionBidHistory.class);
    }

    public List<AuctionBidHistory> findByAuctionItemSeq(Long auctionItemSeq) {
        return dslContext.selectFrom(TB_AUCTION_BID_HISTORY)
                .where(TB_AUCTION_BID_HISTORY.AUCTION_ITEM_SEQ.eq(auctionItemSeq))
                .orderBy(TB_AUCTION_BID_HISTORY.BID_PRICE.desc())
                .fetchInto(AuctionBidHistory.class);
    }

    public Optional<AuctionBidHistory> findById(Long id) {
        AuctionBidHistory result = dslContext.selectFrom(TB_AUCTION_BID_HISTORY)
                .where(TB_AUCTION_BID_HISTORY.SEQ.eq(id))
                .fetchOneInto(AuctionBidHistory.class);
        return Optional.ofNullable(result);
    }

    public AuctionBidHistory save(AuctionBidHistory bid) {
        if (bid.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_AUCTION_BID_HISTORY)
                    .set(TB_AUCTION_BID_HISTORY.USER_SEQ, bid.getUserSeq())
                    .set(TB_AUCTION_BID_HISTORY.AUCTION_ITEM_SEQ, bid.getAuctionItemSeq())
                    .set(TB_AUCTION_BID_HISTORY.BID_PRICE, bid.getBidPrice())
                    .set(TB_AUCTION_BID_HISTORY.IS_INSTANT_BID, bid.getIsInstantBid())
                    .returning()
                    .fetchOneInto(AuctionBidHistory.class);
        } else {
            // Update
            dslContext.update(TB_AUCTION_BID_HISTORY)
                    .set(TB_AUCTION_BID_HISTORY.BID_PRICE, bid.getBidPrice())
                    .set(TB_AUCTION_BID_HISTORY.IS_INSTANT_BID, bid.getIsInstantBid())
                    .where(TB_AUCTION_BID_HISTORY.SEQ.eq(bid.getSeq()))
                    .execute();
            return bid;
        }
    }

    public void deleteById(Long id) {
        dslContext.deleteFrom(TB_AUCTION_BID_HISTORY)
                .where(TB_AUCTION_BID_HISTORY.SEQ.eq(id))
                .execute();
    }
}
