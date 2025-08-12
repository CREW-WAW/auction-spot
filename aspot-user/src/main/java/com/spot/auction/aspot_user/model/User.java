package com.spot.auction.aspot_user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long seq;
    private String role;
    private String email;
    private String password;
    private String nickname;
    private String address;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    private Integer age;
    private String name;

    public void updateInfo(String nickname, String address, Integer age, String name) {
        if (nickname != null) this.nickname = nickname;
        if (address != null) this.address = address;
        if (age != null) this.age = age;
        if (name != null) this.name = name;
    }
}
