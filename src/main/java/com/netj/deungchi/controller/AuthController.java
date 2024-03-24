package com.netj.deungchi.controller;


import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.auth.AppleLoginDto;
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
    public ResponseDto<?> loginKakao(@RequestBody KakaoLoginDto kakaoLoginDto) {
        return authService.kakaoLogin(kakaoLoginDto);
    }

    @PostMapping("/login/apple")
    public ResponseDto<?> loginApple(@RequestBody AppleLoginDto appleLoginDto) {
        return authService.appleLogin(appleLoginDto);
    }
}
