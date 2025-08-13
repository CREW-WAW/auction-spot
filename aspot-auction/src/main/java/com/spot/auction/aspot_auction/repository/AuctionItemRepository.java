package com.spot.auction.aspot_auction.repository;

import com.spot.auction.aspot_auction.model.AuctionItem;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.spot.auction.common.generated.Tables.TB_AUCTION_ITEM;

@Repository
public class AuctionItemRepository {

    private final DSLContext dslContext;

    public AuctionItemRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<AuctionItem> findAll() {
        return dslContext.selectFrom(TB_AUCTION_ITEM)
                .orderBy(TB_AUCTION_ITEM.CREATED_AT.desc())
                .fetchInto(AuctionItem.class);
    }

    public Optional<AuctionItem> findById(Long id) {
        AuctionItem result = dslContext.selectFrom(TB_AUCTION_ITEM)
                .where(TB_AUCTION_ITEM.SEQ.eq(id))
                .fetchOneInto(AuctionItem.class);
        return Optional.ofNullable(result);
    }

    public List<AuctionItem> findByAuctionDate(LocalDate auctionDate) {
        return dslContext.selectFrom(TB_AUCTION_ITEM)
                .where(TB_AUCTION_ITEM.AUCTION_DATE.eq(auctionDate))
                .orderBy(TB_AUCTION_ITEM.SORT_ORDER.asc())
                .fetchInto(AuctionItem.class);
    }

    public List<AuctionItem> findByAuctionDateAndStatus(LocalDate auctionDate, String status) {
        return dslContext.selectFrom(TB_AUCTION_ITEM)
                .where(TB_AUCTION_ITEM.AUCTION_DATE.eq(auctionDate))
                .and(TB_AUCTION_ITEM.STATUS.eq(status))
                .orderBy(TB_AUCTION_ITEM.SORT_ORDER.asc())
                .fetchInto(AuctionItem.class);
    }

    public List<AuctionItem> findByStatus(String status) {
        return dslContext.selectFrom(TB_AUCTION_ITEM)
                .where(TB_AUCTION_ITEM.STATUS.eq(status))
                .orderBy(TB_AUCTION_ITEM.CREATED_AT.desc())
                .fetchInto(AuctionItem.class);
    }

    public AuctionItem save(AuctionItem auctionItem) {
        if (auctionItem.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_AUCTION_ITEM)
                    .set(TB_AUCTION_ITEM.USER_SEQ, auctionItem.getUserSeq())
                    .set(TB_AUCTION_ITEM.TITLE, auctionItem.getTitle())
                    .set(TB_AUCTION_ITEM.DESCRIPTION, auctionItem.getDescription())
                    .set(TB_AUCTION_ITEM.IMAGE_URL, auctionItem.getImageUrl())
                    .set(TB_AUCTION_ITEM.START_PRICE, auctionItem.getStartPrice())
                    .set(TB_AUCTION_ITEM.CATEGORY, auctionItem.getCategory())
                    .set(TB_AUCTION_ITEM.AUCTION_DATE, auctionItem.getAuctionDate())
                    .set(TB_AUCTION_ITEM.STATUS, auctionItem.getStatus())
                    .set(TB_AUCTION_ITEM.SORT_ORDER, auctionItem.getSortOrder())
                    .returning()
                    .fetchOneInto(AuctionItem.class);
        } else {
            // Update
            dslContext.update(TB_AUCTION_ITEM)
                    .set(TB_AUCTION_ITEM.TITLE, auctionItem.getTitle())
                    .set(TB_AUCTION_ITEM.DESCRIPTION, auctionItem.getDescription())
                    .set(TB_AUCTION_ITEM.IMAGE_URL, auctionItem.getImageUrl())
                    .set(TB_AUCTION_ITEM.START_PRICE, auctionItem.getStartPrice())
                    .set(TB_AUCTION_ITEM.CATEGORY, auctionItem.getCategory())
                    .set(TB_AUCTION_ITEM.AUCTION_DATE, auctionItem.getAuctionDate())
                    .set(TB_AUCTION_ITEM.STATUS, auctionItem.getStatus())
                    .set(TB_AUCTION_ITEM.SORT_ORDER, auctionItem.getSortOrder())
                    .where(TB_AUCTION_ITEM.SEQ.eq(auctionItem.getSeq()))
                    .execute();
            return auctionItem;
        }
    }

    public void deleteById(Long id) {
        dslContext.deleteFrom(TB_AUCTION_ITEM)
                .where(TB_AUCTION_ITEM.SEQ.eq(id))
                .execute();
    }
}
