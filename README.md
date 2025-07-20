# Aspot Platform (MSA 기반 경매 서비스)

## 📦 프로젝트 개요

Aspot은 동네 인증을 기반으로 한 지역 커뮤니티 경매 서비스입니다.  
사용자 인증부터 경매 등록/입찰, 알림, 관리자 기능까지 MSA 아키텍처로 구성되어 있습니다.

---

## 🧱 프로젝트 구조

- **aspot-discovery-server**: Spring Cloud Eureka 기반 서비스 등록 서버
- **aspot-gateway**: Spring Cloud Gateway를 활용한 진입점
- **aspot-user**: 사용자 및 인증 서비스 (일반 사용자 + 관리자 분리 가능)
- **aspot-auction**: 경매 등록/입찰/낙찰 로직 처리
- **aspot-notification**: 알림 처리 (FCM)
- **aspot-location**: 지역 인증 및 위치 정보 처리
- **aspot-common**: 공통 라이브러리 모듈 (DTO, Exception, Config 등)

---

## 🛠️ 기술 스택

### 1. Language / Framework
- **Backend**: Java 17, Spring Boot 3.4.5, Spring Cloud, JOOQ
- **Frontend**: (추후 정의)
- **Mobile**: (추후 정의)

### 2. Infra
- **서버 환경**: AWS EC2
- **DBMS**: MySQL 8.x, Redis
- **배포**: GitHub Actions
- **도메인**: Route 53

### 3. 실시간 처리 & 메시징
- **메시지 플랫폼**: Amazon SQS (또는 Kafka)
- **알림 시스템**: Firebase Cloud Messaging (FCM)

### 4. 인증/Auth
- **JWT** 기반 사용자 인증
- **OAuth 2.0** (Kakao, Naver 연동 예정)
- **Spring Security**

### 5. Tools
- **버전 관리**: Git
- **협업 도구**: Notion, Discord
- **API 문서화**: Swagger (Springdoc OpenAPI), Postman

### 6. 모니터링
- **로그 수집**: Logback + ELK Stack
- **모니터링**: Prometheus, Grafana

---

## ⚙️ 공통 Gradle 구성

```groovy
// settings.gradle
rootProject.name = 'aspot-platform'

include 'aspot-common'
include 'aspot-discovery-server'
include 'aspot-gateway'
include 'aspot-user'
include 'aspot-auction'
include 'aspot-notification'
include 'aspot-location'
```

