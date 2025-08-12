package com.spot.auction.aspot_notification.repository;

import com.spot.auction.aspot_notification.model.ChatRoom;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spot.auction.aspot_notification.generated.Tables.TB_CHAT_ROOM;

@Repository
public class ChatRoomRepository {

    private final DSLContext dslContext;

    public ChatRoomRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public ChatRoom findById(Long id) {
        return dslContext.selectFrom(TB_CHAT_ROOM)
                .where(TB_CHAT_ROOM.SEQ.eq(id))
                .fetchOneInto(ChatRoom.class);
    }

    public List<ChatRoom> findBySellerSeqOrBuyerSeq(Long sellerSeq, Long buyerSeq) {
        return dslContext.selectFrom(TB_CHAT_ROOM)
                .where(TB_CHAT_ROOM.SELLER_SEQ.eq(sellerSeq))
                .or(TB_CHAT_ROOM.BUYER_SEQ.eq(buyerSeq))
                .orderBy(TB_CHAT_ROOM.CREATED_AT.desc())
                .fetchInto(ChatRoom.class);
    }
}
