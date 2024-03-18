package com.netj.deungchi.service;

import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.mountain.MountainCooridateDto;
import com.netj.deungchi.repository.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoUtils {

    private static final double EARTH_RADIUS = 6371; // 지구 반지름 (단위: km)
    private final MountainRepository mountainRepository;

    // 두 지점 간의 거리 계산
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 위도 및 경도를 라디안 단위로 변환
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        // 위도 및 경도의 차이의 제곱 값에 대한 합의 제곱근
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산 (m로 변환)
        return EARTH_RADIUS * c * 1000;
    }

    // 반경 내의 산 추출
    public ResponseDto<?> findMountainsInRadius(double userLat, double userLng, double radius) {

        // 모든 산 데이터 가져오기
        List<Mountain> allMountains = mountainRepository.findAll();
        List<MountainCooridateDto> mountainCooridateDtoList = allMountains.stream().map(MountainCooridateDto::new).toList();

        List<MountainCooridateDto> mountainsInRadius = new ArrayList<>();

        // 반경 내의 산 추출
        for (MountainCooridateDto mountain : mountainCooridateDtoList) {
            if (mountain.getLatitude() != null && mountain.getLongitude() != null) {
                double mountainLat = mountain.getLatitude();
                double mountainLng = mountain.getLongitude();
                double distance = calculateDistance(userLat, userLng, mountainLat, mountainLng);
                if (distance <= radius) {
                    mountainsInRadius.add(mountain);
                }
            }
        }


        return ResponseDto.success(mountainsInRadius);
    }
}
