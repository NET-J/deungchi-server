package com.netj.deungchi.service;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.auth.KakaoLoginDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    public final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;


    public ResponseDto<?> kakaoLogin(KakaoLoginDto kakaoLoginDto) {
        Date now = new Date();
        System.out.println(kakaoLoginDto.getId());
        if (kakaoLoginDto.getId().isEmpty()) {
            return ResponseDto.fail(400, "error", "param error");
        }
        Optional<Member> member = Optional.ofNullable(memberRepository.findByProviderId("kakao", kakaoLoginDto.getId()));

        if (member.get().getDeleted_at() != null) {
            return ResponseDto.fail(400, "error", "leave member");
        }

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .provider("kakao")
                    .provider_id(kakaoLoginDto.getId())
                    .email(kakaoLoginDto.getEmail())
                    .nickname(kakaoLoginDto.getNickname())
                    .profile_image(kakaoLoginDto.getProfileImage())
                    .build();
            memberRepository.save(newMember);

            member = Optional.of(newMember);
        }

        String accessToken = jwtProvider.getAccessToken(member.get().getId());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("member", member);

//        String token = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
//                .setIssuer("fresh") // (2)
//                .setIssuedAt(now) // (3)
//                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
//                .claim("id", member.get().getId()) // (5)
//                .signWith(SignatureAlgorithm.HS256, "deungchi-jwt-secret") // (6)
//                .compact();
        return ResponseDto.success(result);
    }
}
