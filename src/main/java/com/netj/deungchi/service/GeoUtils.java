package com.netj.deungchi.service;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.mountain.MountainCooridateDto;

import java.util.ArrayList;
import java.util.List;

public class GeoUtils {

    private static final double EARTH_RADIUS = 6371; // 지구 반지름 (단위: km)

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

        // 거리 계산
        return EARTH_RADIUS * c;
    }

    // 반경 내의 산 추출
    public List<MountainCooridateDto> findMountainsInRadius(double userLat, double userLng, double radius) {
        List<MountainCooridateDto> mountainsInRadius = new ArrayList<>();

        // 모든 산 데이터 가져오기 (예: mountainRepository.findAll())
        List<MountainCooridateDto> allMountains = getAllMountains(); // 이 메소드는 실제로 데이터베이스에서 모든 산을 가져오는 코드로 구현되어야 합니다.

        // 반경 내의 산 추출
        for (Mountain mountain : allMountains) {
            double mountainLat = mountain.getLatitude();
            double mountainLng = mountain.getLongitude();
            double distance = calculateDistance(userLat, userLng, mountainLat, mountainLng);
            if (distance <= radius) {
                mountainsInRadius.add(mountain);
            }
        }

        return mountainsInRadius;
    }

    // 비슷한 위도와 경도 찾기
    public static ResponseDto<?> findSimilarCoordinates(double targetLat, double targetLng, double radius) {
        List<?> similarCoordinates = new ArrayList<>();

        // 위도 및 경도의 범위 계산
        double minLat = targetLat - radius;
        double maxLat = targetLat + radius;
        double minLng = targetLng - radius;
        double maxLng = targetLng + radius;

        // 반복문을 사용하여 비슷한 좌표 찾기
        for (double lat = minLat; lat <= maxLat; lat += 0.1) {
            for (double lng = minLng; lng <= maxLng; lng += 0.1) {
                double distance = calculateDistance(targetLat, targetLng, lat, lng);
                if (distance <= radius) {
                    similarCoordinates.add(new LatLng(lat, lng));
                }
            }
        }

        return ResponseDto.success(similarCoordinates);
    }
}
