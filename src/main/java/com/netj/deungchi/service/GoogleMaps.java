package com.netj.deungchi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleMaps {

    private static final String GOOGLE_MAPS_API_KEY = "AIzaSyC8wntavIryg-gziBco8EW7GMIov_HoeNk";

    public static void main(String[] args) {
        String origin = "출발지 좌표"; // 출발지 좌표 (예: "37.7749,-122.4194")
        String destination = "목적지 좌표"; // 목적지 좌표 (예: "37.7749,-122.4194")

        double distance = getDistance(origin, destination);
        double travelTime = getTravelTime(origin, destination);

        System.out.println("목적지까지의 거리: " + distance + "km");
        System.out.println("예상 이동 시간: " + travelTime + "분");
    }

    private static double getDistance(String origin, String destination) {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="
                + origin + "&destinations=" + destination + "&key=" + GOOGLE_MAPS_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // 파싱하여 거리 정보 추출
        // 여기서는 JSON 파싱을 가정하지만 실제로는 Gson 또는 Jackson과 같은 라이브러리를 사용할 수 있습니다.
        // JSON 응답 예시: {"rows":[{"elements":[{"distance":{"text":"12.3 km","value":12345},"duration":{"text":"20 mins","value":1200}}]}]}

        // 거리 정보 추출 후 반환 (실제로는 JSON 파싱을 통해 값을 추출해야 함)
        return 12.3; // 임의의 값으로 대체 (실제 거리 값은 JSON 파싱 결과에서 가져와야 함)
    }

    private static double getTravelTime(String origin, String destination) {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="
                + origin + "&destinations=" + destination + "&key=" + GOOGLE_MAPS_API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // 파싱하여 이동 시간 정보 추출
        // 여기서는 JSON 파싱을 가정하지만 실제로는 Gson 또는 Jackson과 같은 라이브러리를 사용할 수 있습니다.
        // JSON 응답 예시: {"rows":[{"elements":[{"distance":{"text":"12.3 km","value":12345},"duration":{"text":"20 mins","value":1200}}]}]}

        // 이동 시간 정보 추출 후 반환 (실제로는 JSON 파싱을 통해 값을 추출해야 함)
        return 20; // 임의의 값으로 대체 (실제 이동 시간 값은 JSON 파싱 결과에서 가져와야 함)
    }
}
