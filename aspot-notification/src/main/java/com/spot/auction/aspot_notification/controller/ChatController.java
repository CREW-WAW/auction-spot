package com.spot.auction.aspot_notification.controller;

import com.spot.auction.aspot_notification.dto.ChatRoomDto;
import com.spot.auction.aspot_notification.dto.ChatMessageDto;
import com.spot.auction.aspot_notification.dto.ChatMessageRequest;
import com.spot.auction.aspot_notification.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<ChatRoomDto>> getMyChatRooms() {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        List<ChatRoomDto> chatRooms = chatService.getMyChatRooms(userId);
        return ResponseEntity.ok(chatRooms);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<ChatRoomDto> getChatRoomDetail(@PathVariable Long roomId) {
        ChatRoomDto chatRoom = chatService.getChatRoomDetail(roomId);
        return ResponseEntity.ok(chatRoom);
    }

    @PostMapping("/{roomId}/message")
    public ResponseEntity<ChatMessageDto> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessageRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        ChatMessageDto message = chatService.sendMessage(roomId, userId, request);
        return ResponseEntity.ok(message);
    }
}
