package com.netj.deungchi.service;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.login.KakaoLoginDto;
import com.netj.deungchi.repository.MemberRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    public final MemberRepository memberRepository;


    public ResponseDto<?> login(String provider, KakaoLoginDto kakaoLoginDto) {
        Date now = new Date();

        Optional<Member> member = Optional.ofNullable(memberRepository.findByProviderId(provider, kakaoLoginDto.getId()));

        if (member.isEmpty()) {
            member = Optional.ofNullable(Member.builder()
                    .email(kakaoLoginDto.getEmail())
                    .profile_image(kakaoLoginDto.getProfile_image())
                    .provider(provider)
                    .provider_id(kakaoLoginDto.getId())
                    .build());
        }

        String token = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("id", member.get().getId()) // (5)
                .signWith(SignatureAlgorithm.HS256, "deungchi-jwt-secret") // (6)
                .compact();

        return ResponseDto.success(token);
    }
}
