package com.spot.auction.common.constant;

/**
 * 공통 상수 클래스
 */
public class CommonConstants {
    
    // API 응답 메시지
    public static final String SUCCESS_MESSAGE = "Success";
    public static final String ERROR_MESSAGE = "Error occurred";
    
    // 데이터베이스 관련
    public static final String DEFAULT_SCHEMA = "aspotdb";
    
    // Kafka 토픽
    public static final String USER_EVENTS_TOPIC = "aspot-user-events";
    public static final String AUCTION_EVENTS_TOPIC = "aspot-auction-events";
    public static final String LOCATION_EVENTS_TOPIC = "aspot-location-events";
    public static final String NOTIFICATION_EVENTS_TOPIC = "aspot-notification-events";
    
    // 사용자 역할
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    
    // 경매 상태
    public static final String AUCTION_STATUS_PENDING = "PENDING";
    public static final String AUCTION_STATUS_ACTIVE = "ACTIVE";
    public static final String AUCTION_STATUS_CLOSED = "CLOSED";
    public static final String AUCTION_STATUS_CANCELLED = "CANCELLED";
    
    // 위치 인증 상태
    public static final String LOCATION_AUTH_PENDING = "PENDING";
    public static final String LOCATION_AUTH_APPROVED = "APPROVED";
    public static final String LOCATION_AUTH_REJECTED = "REJECTED";
    public static final String LOCATION_AUTH_EXPIRED = "EXPIRED";
}
