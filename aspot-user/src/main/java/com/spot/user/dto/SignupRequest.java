package com.spot.user.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String password;
    private String nickname;
    private String address;
    private Integer age;
    private String name;
}
