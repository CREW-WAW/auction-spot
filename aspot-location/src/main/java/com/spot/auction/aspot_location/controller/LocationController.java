package com.spot.auction.aspot_location.controller;

import com.spot.auction.aspot_location.dto.LocationDto;
import com.spot.auction.aspot_location.dto.LocationVerifyRequest;
import com.spot.auction.aspot_location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/verify")
    public ResponseEntity<LocationDto> verifyLocation(@RequestBody LocationVerifyRequest request) {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        LocationDto verifiedLocation = locationService.verifyLocation(userId, request);
        return ResponseEntity.ok(verifiedLocation);
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<LocationDto>> getNearbyLocations() {
        // TODO: JWT 토큰에서 사용자 정보 추출
        Long userId = 1L; // 임시 값
        List<LocationDto> nearbyLocations = locationService.getNearbyLocations(userId);
        return ResponseEntity.ok(nearbyLocations);
    }
}
