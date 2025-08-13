package com.spot.user.service;

import com.spot.user.dto.UserDto;
import com.spot.user.dto.UserUpdateRequest;
import com.spot.user.model.User;
import com.spot.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDto(user);
    }

    @Transactional
    public UserDto updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.updateInfo(request.getNickname(), request.getAddress(), request.getAge(), request.getName());
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(userId);
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .seq(user.getSeq())
                .role(user.getRole())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .address(user.getAddress())
                .isVerified(user.getIsVerified())
                .createdAt(user.getCreatedAt())
                .age(user.getAge())
                .name(user.getName())
                .build();
    }
}
