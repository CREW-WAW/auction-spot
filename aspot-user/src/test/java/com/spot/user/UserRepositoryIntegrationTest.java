package com.spot.user;

import com.spot.auction.common.generated.enums.TbUserRole;
import com.spot.user.model.User;
import com.spot.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .email("test@example.com")
                .password("password123")
                .nickname("testuser")
                .name("Test User")
                .address("Test Address")
                .age(25)
                .isVerified(true)
                .role(TbUserRole.USER)
                .build();
    }

    @Test
    void testSaveUser() {
        // 사용자 저장 테스트
        User savedUser = userRepository.save(testUser);
        
        assertNotNull(savedUser, "Saved user should not be null");
        assertNotNull(savedUser.getSeq(), "Saved user should have an ID");
        assertEquals(testUser.getEmail(), savedUser.getEmail(), "Email should match");
        assertEquals(testUser.getNickname(), savedUser.getNickname(), "Nickname should match");
    }

    @Test
    void testFindByEmail() {
        // 사용자 저장
        User savedUser = userRepository.save(testUser);
        
        // 이메일로 사용자 찾기
        var foundUser = userRepository.findByEmail(testUser.getEmail());
        
        assertTrue(foundUser.isPresent(), "User should be found by email");
        assertEquals(savedUser.getSeq(), foundUser.get().getSeq(), "User ID should match");
        assertEquals(savedUser.getEmail(), foundUser.get().getEmail(), "Email should match");
    }

    @Test
    void testFindById() {
        // 사용자 저장
        User savedUser = userRepository.save(testUser);
        
        // ID로 사용자 찾기
        var foundUser = userRepository.findById(savedUser.getSeq());
        
        assertTrue(foundUser.isPresent(), "User should be found by ID");
        assertEquals(savedUser.getSeq(), foundUser.get().getSeq(), "User ID should match");
    }

    @Test
    void testExistsByEmail() {
        // 사용자 저장
        userRepository.save(testUser);
        
        // 이메일 존재 여부 확인
        boolean exists = userRepository.existsByEmail(testUser.getEmail());
        assertTrue(exists, "User should exist with given email");
        
        // 존재하지 않는 이메일 확인
        boolean notExists = userRepository.existsByEmail("nonexistent@example.com");
        assertFalse(notExists, "User should not exist with non-existent email");
    }

    @Test
    void testUpdateUser() {
        // 사용자 저장
        User savedUser = userRepository.save(testUser);
        
        // 사용자 정보 업데이트
        savedUser.updateInfo("updateduser", "Updated Address", 30, "Updated Name");
        User updatedUser = userRepository.save(savedUser);
        
        // 업데이트된 정보 확인
        assertEquals("updateduser", updatedUser.getNickname(), "Nickname should be updated");
        assertEquals("Updated Address", updatedUser.getAddress(), "Address should be updated");
        assertEquals(30, updatedUser.getAge(), "Age should be updated");
        assertEquals("Updated Name", updatedUser.getName(), "Name should be updated");
    }

    @Test
    void testDeleteUser() {
        // 사용자 저장
        User savedUser = userRepository.save(testUser);
        
        // 사용자 삭제
        userRepository.deleteById(savedUser.getSeq());
        
        // 삭제 확인
        var foundUser = userRepository.findById(savedUser.getSeq());
        assertFalse(foundUser.isPresent(), "User should be deleted");
        
        boolean exists = userRepository.existsByEmail(testUser.getEmail());
        assertFalse(exists, "User should not exist after deletion");
    }
}
