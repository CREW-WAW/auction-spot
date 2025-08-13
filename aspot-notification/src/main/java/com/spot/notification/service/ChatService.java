package com.spot.notification.service;

import com.spot.notification.dto.ChatRoomDto;
import com.spot.notification.dto.ChatMessageDto;
import com.spot.notification.dto.ChatMessageRequest;
import com.spot.notification.model.ChatRoom;
import com.spot.notification.model.ChatMessage;
import com.spot.notification.repository.ChatRoomRepository;
import com.spot.notification.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public List<ChatRoomDto> getMyChatRooms(Long userId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findBySellerSeqOrBuyerSeq(userId, userId);
        return chatRooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ChatRoomDto getChatRoomDetail(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        
        List<ChatMessage> messages = chatMessageRepository.findByChatRoomSeqOrderByCreatedAtAsc(roomId);
        
        ChatRoomDto dto = convertToDto(chatRoom);
        dto.setMessages(messages.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        
        return dto;
    }

    @Transactional
    public ChatMessageDto sendMessage(Long roomId, Long userId, ChatMessageRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // 사용자가 해당 채팅방의 참여자인지 확인
        if (!chatRoom.getSellerSeq().equals(userId) && !chatRoom.getBuyerSeq().equals(userId)) {
            throw new RuntimeException("Not authorized to send message to this chat room");
        }

        ChatMessage message = ChatMessage.builder()
                .chatRoomSeq(roomId)
                .senderSeq(userId)
                .content(request.getContent())
                .build();

        ChatMessage savedMessage = chatMessageRepository.save(message);
        return convertToDto(savedMessage);
    }

    private ChatRoomDto convertToDto(ChatRoom chatRoom) {
        return ChatRoomDto.builder()
                .seq(chatRoom.getSeq())
                .sellerSeq(chatRoom.getSellerSeq())
                .buyerSeq(chatRoom.getBuyerSeq())
                .auctionItemSeq(chatRoom.getAuctionItemSeq())
                .openedAt(chatRoom.getOpenedAt())
                .closedAt(chatRoom.getClosedAt())
                .createdAt(chatRoom.getCreatedAt())
                .build();
    }

    private ChatMessageDto convertToDto(ChatMessage message) {
        return ChatMessageDto.builder()
                .seq(message.getSeq())
                .chatRoomSeq(message.getChatRoomSeq())
                .senderSeq(message.getSenderSeq())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
