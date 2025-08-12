## 기술 스택

### 1. Skill (Language / Framework / Library ..)

- **Backend**:
    - Java 21
    - Spring Boot 3.4.5
    - Spring-cloud-msa
    - Kafka
    - JOOQ
- **Frontend**: (미정)
- **Mobile**: Flutter

### 2. Infra

- **서버 환경**: AWS EC2
- **DBMS**: MySQL 8.x, Redis
- **배포**: GitHub Actions
- **도메인**: Route 53

### 3. 실시간 처리 & 메시징

- **메시지 플랫폼**: Kafka
- **알림 시스템**: Firebase Cloud Messaging (FCM)

### 4. Auth

- **JWT** 기반 사용자 인증
- **OAuth 2.0** (Kakao, Naver 연동 예정)
- **Spring Security**

### 5. Tools

- **버전 관리**: Git
- **협업 도구**: Notion, Slack
- **API 문서화**: Swagger (Springdoc OpenAPI), Postman

### 6. Monitor

- **로그 수집**: Logback + ELK Stack
- **모니터링**: Prometheus, Grafana

---
### 서비스 API 목록

### 사용자 인증 및 계정 관리(Security로 OAuth로 정보받는정도까지)

```
[POST]   /api/auth/signup              회원가입
[POST]   /api/auth/login               로그인
[GET]    /api/users/me                 내 정보 조회
[PUT]    /api/users/me                 내 정보 수정
[DELETE] /api/users/me                 회원 탈퇴
```

### 경매 물품 등록 및 조회

```
[POST]   /api/items                    경매 물품 등록
[GET]    /api/items                   전체 경매 물품 목록 조회
[GET]    /api/items/{id}              특정 경매 물품 상세 조회
[PUT]    /api/items/{id}              경매 물품 수정
[DELETE] /api/items/{id}              경매 물품 삭제
```

### 경매 참여 (입찰 등)

```
[POST]   /api/bids                     입찰 요청
[GET]    /api/bids/my                  내 입찰 내역 조회
```

### 경매 진행 및 결과

```
[GET]    /api/auctions/today          오늘 진행될 경매 물품 리스트
[GET]    /api/auctions/live           현재 진행 중인 경매 목록
[GET]    /api/auctions/result         낙찰 결과 확인
```

### 거래 채팅

```
[GET]    /api/chats                   내 채팅 목록 조회
[GET]    /api/chats/{roomId}          채팅방 상세 조회
[POST]   /api/chats/{roomId}/message  채팅 메시지 전송
```

### 동네 인증 및 위치

```
[POST]   /api/locations/verify        위치 인증 요청
[GET]    /api/locations/nearby        내 주변 동네 목록 조회
```

### 신고 관리

```
[POST]    /api/admin/reports/{id}/open 신고 신청
```
---
### 관리자(CMS) API 목록

### 회원 관리

```
[GET]    /api/admin/users                  전체 회원 목록 조회
[GET]    /api/admin/users/{id}             특정 회원 상세 조회
[PUT]    /api/admin/users/{id}/suspend     회원 정지 처리
[DELETE] /api/admin/users/{id}             회원 강제 탈퇴
```

### 경매 물품 관리

```
[GET]    /api/admin/items                  전체 경매 물품 목록 조회
[GET]    /api/admin/items/{id}             특정 물품 상세 조회
[PUT]    /api/admin/items/{id}/status      물품 상태 변경 (예: 삭제, 숨김 등)
[DELETE] /api/admin/items/{id}             물품 강제 삭제
```

### 신고 관리

```
[GET]    /api/admin/reports                전체 신고 목록 조회
[GET]    /api/admin/reports/{id}           신고 상세 조회
[PUT]    /api/admin/reports/{id}/resolve   신고 처리 완료
```

### 관리자 알림

```
[POST]   /api/admin/notifications/send     특정 사용자 또는 전체 대상 알림 전송

```
```sql
CREATE TABLE tb_user (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    role ENUM('USER', 'ADMIN') NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    is_verified BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    age INT,
    name VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_location (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    sido VARCHAR(50) NOT NULL,
    sigungu VARCHAR(50) NOT NULL,
    eupmyeondong VARCHAR(50) NOT NULL,
    zipcode VARCHAR(20),
    lat DOUBLE,
    lng DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_user_location_auth (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_seq BIGINT NOT NULL,
    location_seq BIGINT NOT NULL,
    auth_status ENUM('PENDING', 'APPROVED', 'REJECTED', 'EXPIRED') NOT NULL,
    requested_at DATETIME NOT NULL,
    approved_at DATETIME,
    expired_at DATETIME,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_seq) REFERENCES tb_user(seq),
    FOREIGN KEY (location_seq) REFERENCES tb_location(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_auction_item (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_seq BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(500),
    start_price INT NOT NULL,
    category VARCHAR(100),
    auction_date DATE NOT NULL,
    status VARCHAR(50),
    sort_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_seq) REFERENCES tb_user(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_auction_bid_history (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_seq BIGINT NOT NULL,
    auction_item_seq BIGINT NOT NULL,
    bid_price INT NOT NULL,
    is_instant_bid BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_seq) REFERENCES tb_user(seq),
    FOREIGN KEY (auction_item_seq) REFERENCES tb_auction_item(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_chat_room (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    seller_seq BIGINT NOT NULL,
    buyer_seq BIGINT NOT NULL,
    auction_item_seq BIGINT NOT NULL,
    opened_at TIMESTAMP NOT NULL,
    closed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_seq) REFERENCES tb_user(seq),
    FOREIGN KEY (buyer_seq) REFERENCES tb_user(seq),
    FOREIGN KEY (auction_item_seq) REFERENCES tb_auction_item(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_chat_message (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    chat_room_seq BIGINT NOT NULL,
    sender_seq BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_room_seq) REFERENCES tb_chat_room(seq),
    FOREIGN KEY (sender_seq) REFERENCES tb_user(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE tb_report (
    seq BIGINT AUTO_INCREMENT PRIMARY KEY,
    reporter_seq BIGINT NOT NULL,
    user_seq BIGINT NOT NULL,
    report_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (reporter_seq) REFERENCES tb_user(seq),
    FOREIGN KEY (user_seq) REFERENCES tb_user(seq)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
