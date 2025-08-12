package com.spot.auction.aspot_location.dto;

import lombok.Data;

@Data
public class LocationVerifyRequest {
    private String sido;
    private String sigungu;
    private String eupmyeondong;
    private String zipcode;
    private Double lat;
    private Double lng;
}
