package com.spot.user.dto;

import com.spot.auction.common.generated.enums.TbUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long seq;
    private TbUserRole role;
    private String email;
    private String nickname;
    private String address;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    private Integer age;
    private String name;
}
