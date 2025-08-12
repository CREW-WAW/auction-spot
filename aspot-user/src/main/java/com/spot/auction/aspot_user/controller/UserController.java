package com.spot.auction.aspot_user.controller;

import com.spot.auction.aspot_user.dto.UserDto;
import com.spot.auction.aspot_user.dto.UserUpdateRequest;
import com.spot.auction.aspot_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMyInfo() {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        UserDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<UserDto> updateMyInfo(@RequestBody UserUpdateRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        UserDto updatedUser = userService.updateUser(userId, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyAccount() {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
