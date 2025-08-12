package com.spot.auction.aspot_location.service;

import com.spot.auction.aspot_location.dto.LocationDto;
import com.spot.auction.aspot_location.dto.LocationVerifyRequest;
import com.spot.auction.aspot_location.model.Location;
import com.spot.auction.aspot_location.model.UserLocationAuth;
import com.spot.auction.aspot_location.repository.LocationRepository;
import com.spot.auction.aspot_location.repository.UserLocationAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserLocationAuthRepository userLocationAuthRepository;

    @Transactional
    public LocationDto verifyLocation(Long userId, LocationVerifyRequest request) {
        // 위치 정보 저장 또는 업데이트
        Location location = Location.builder()
                .sido(request.getSido())
                .sigungu(request.getSigungu())
                .eupmyeondong(request.getEupmyeondong())
                .zipcode(request.getZipcode())
                .lat(request.getLat())
                .lng(request.getLng())
                .build();

        Location savedLocation = locationRepository.save(location);

        // 사용자 위치 인증 요청 생성
        UserLocationAuth auth = UserLocationAuth.builder()
                .userSeq(userId)
                .locationSeq(savedLocation.getSeq())
                .authStatus(UserLocationAuth.AuthStatus.PENDING)
                .requestedAt(LocalDateTime.now())
                .isActive(true)
                .build();

        userLocationAuthRepository.save(auth);

        return convertToDto(savedLocation);
    }

    public List<LocationDto> getNearbyLocations(Long userId) {
        // 사용자의 인증된 위치 기반으로 주변 위치 조회
        List<Location> nearbyLocations = locationRepository.findNearbyLocations(userId);
        return nearbyLocations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private LocationDto convertToDto(Location location) {
        return LocationDto.builder()
                .seq(location.getSeq())
                .sido(location.getSido())
                .sigungu(location.getSigungu())
                .eupmyeondong(location.getEupmyeondong())
                .zipcode(location.getZipcode())
                .lat(location.getLat())
                .lng(location.getLng())
                .createdAt(location.getCreatedAt())
                .build();
    }
}
