package com.spot.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long seq;
    private Long chatRoomSeq;
    private Long senderSeq;
    private String content;
    private LocalDateTime createdAt;
}
