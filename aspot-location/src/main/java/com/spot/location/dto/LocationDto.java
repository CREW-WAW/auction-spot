package com.spot.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private Long seq;
    private String sido;
    private String sigungu;
    private String eupmyeondong;
    private String zipcode;
    private Double lat;
    private Double lng;
    private LocalDateTime createdAt;
}
