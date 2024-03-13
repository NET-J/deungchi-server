package com.netj.deungchi.service;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.dto.ResponseDto;
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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService {
    public final MemberRepository memberRepository;


    public ResponseDto<?> login(String provider, String provider_id) {
        Date now = new Date();

        Member member = memberRepository.findByProviderId(provider, provider_id);

        String token = Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("id", member.getId()) // (5)
                .signWith(SignatureAlgorithm.HS256, "deungchi-jwt-secret") // (6)
                .compact();

        return ResponseDto.success(token);
    }
}
