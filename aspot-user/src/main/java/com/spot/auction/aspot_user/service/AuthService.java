package com.spot.auction.aspot_user.service;

import com.spot.auction.aspot_user.dto.AuthResponse;
import com.spot.auction.aspot_user.dto.LoginRequest;
import com.spot.auction.aspot_user.dto.SignupRequest;
import com.spot.auction.aspot_user.dto.UserDto;
import com.spot.auction.aspot_user.model.User;
import com.spot.auction.aspot_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // 사용자 생성
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .address(request.getAddress())
                .age(request.getAge())
                .name(request.getName())
                .role("USER")
                .isVerified(false)
                .build();

        User savedUser = userRepository.save(user);

        // JWT 토큰 생성
        String token = jwtService.generateToken(savedUser.getEmail());
        String refreshToken = jwtService.generateRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .user(convertToDto(savedUser))
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // JWT 토큰 생성
        String token = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .user(convertToDto(user))
                .build();
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
