package com.spot.user.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String nickname;
    private String address;
    private Integer age;
    private String name;
}
