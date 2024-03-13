package com.netj.deungchi.controller;


import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.auth.KakaoLoginDto;
import com.netj.deungchi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;

    @PostMapping("/login/kakao")
    public ResponseDto<?> loginKakao(@RequestParam KakaoLoginDto kakaoLoginDto) {
        return authService.kakaoLogin(kakaoLoginDto);
//        System.out.println("code = " + code);
//
//        // 1. header 생성
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
//
//        // 2. body 생성
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("grant_type", "authorization_code"); //고정값
//        params.add("client_id", "1093c24b880414c146c07ea6c5afdc38");
//        params.add("redirect_uri", "http://localhost:8080/oauth2/kakao"); //등록한 redirect uri
//        params.add("code", code);
//
//        // 3. header + body
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, httpHeaders);
//
//        // 4. http 요청하기
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Object> response = restTemplate.exchange(
//                "https://kauth.kakao.com/oauth/token",
//                HttpMethod.POST,
//                httpEntity,
//                Object.class
//        );
//
//
//        // 로그인 처리할건지 회원가입 처리할건지
//
//        System.out.println("response = " + response);

//        return authService.login("kakao", "123");
    }
}
