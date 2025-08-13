package com.spot.auction.common.util;

import com.spot.auction.common.generated.enums.TbUserRole;
import com.spot.auction.common.generated.enums.TbUserLocationAuthAuthStatus;

/**
 * ENUM 타입과 DTO 간의 매핑을 위한 유틸리티 클래스
 */
public class EnumMapper {

    /**
     * TbUserRole ENUM을 String으로 변환
     */
    public static String userRoleToString(TbUserRole role) {
        return role != null ? role.getLiteral() : null;
    }

    /**
     * String을 TbUserRole ENUM으로 변환
     */
    public static TbUserRole stringToUserRole(String role) {
        if (role == null) return null;
        try {
            return TbUserRole.valueOf(role);
        } catch (IllegalArgumentException e) {
            return TbUserRole.USER; // 기본값
        }
    }

    /**
     * TbUserLocationAuthAuthStatus ENUM을 String으로 변환
     */
    public static String authStatusToString(TbUserLocationAuthAuthStatus status) {
        return status != null ? status.getLiteral() : null;
    }

    /**
     * String을 TbUserLocationAuthAuthStatus ENUM으로 변환
     */
    public static TbUserLocationAuthAuthStatus stringToAuthStatus(String status) {
        if (status == null) return null;
        try {
            return TbUserLocationAuthAuthStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            return null; // 기본값 없음
        }
    }

    /**
     * Boolean을 Byte로 변환 (레거시 시스템 호환성)
     */
    public static Byte booleanToByte(Boolean value) {
        return value != null ? (value ? (byte) 1 : (byte) 0) : null;
    }

    /**
     * Byte를 Boolean으로 변환 (레거시 시스템 호환성)
     */
    public static Boolean byteToBoolean(Byte value) {
        return value != null ? value == 1 : null;
    }
}
