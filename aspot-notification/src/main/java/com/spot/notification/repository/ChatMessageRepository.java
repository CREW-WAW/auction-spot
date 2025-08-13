package com.spot.notification.repository;

import com.spot.notification.model.ChatMessage;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spot.auction.common.generated.Tables.TB_CHAT_MESSAGE;

@Repository
public class ChatMessageRepository {

    private final DSLContext dslContext;

    public ChatMessageRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<ChatMessage> findByChatRoomSeqOrderByCreatedAtAsc(Long chatRoomSeq) {
        return dslContext.selectFrom(TB_CHAT_MESSAGE)
                .where(TB_CHAT_MESSAGE.CHAT_ROOM_SEQ.eq(chatRoomSeq))
                .orderBy(TB_CHAT_MESSAGE.CREATED_AT.asc())
                .fetchInto(ChatMessage.class);
    }

    public ChatMessage save(ChatMessage message) {
        if (message.getSeq() == null) {
            // Insert
            return dslContext.insertInto(TB_CHAT_MESSAGE)
                    .set(TB_CHAT_MESSAGE.CHAT_ROOM_SEQ, message.getChatRoomSeq())
                    .set(TB_CHAT_MESSAGE.SENDER_SEQ, message.getSenderSeq())
                    .set(TB_CHAT_MESSAGE.CONTENT, message.getContent())
                    .returning()
                    .fetchOneInto(ChatMessage.class);
        } else {
            // Update
            dslContext.update(TB_CHAT_MESSAGE)
                    .set(TB_CHAT_MESSAGE.CONTENT, message.getContent())
                    .where(TB_CHAT_MESSAGE.SEQ.eq(message.getSeq()))
                    .execute();
            return message;
        }
    }
}
