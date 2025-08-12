package com.spot.auction.aspot_user.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
