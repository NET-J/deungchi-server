package com.netj.deungchi.service;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.MemberTerm;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.auth.AppleLoginDto;
import com.netj.deungchi.dto.auth.KakaoLoginDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.repository.MemberRepository;
import com.netj.deungchi.repository.MemberTermRepository;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
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
    public final MemberTermRepository memberTermRepository;

    public ResponseDto<?> kakaoLogin(KakaoLoginDto kakaoLoginDto) {
        Date now = new Date();
        System.out.println(kakaoLoginDto.getId());
        if (kakaoLoginDto.getId().isEmpty()) {
            return ResponseDto.fail(400, "error", "param error");
        }
        Optional<Member> member = Optional.ofNullable(memberRepository.findByProviderId("kakao", kakaoLoginDto.getId()));

        if (!member.isEmpty() && member.get().getDeleted_at() != null) {
            return ResponseDto.fail(400, "error", "leave member");
        }
        boolean isCreate = false;

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
            isCreate = true;
        }

        String accessToken = jwtProvider.getAccessToken(member.get().getId());

        Long memberTermCount = memberTermRepository.countMemberTerm(member.get().getId());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("member", member);
        result.put("isCreate", isCreate);
        result.put("termCount", memberTermCount);

        return ResponseDto.success(result);
    }

    public ResponseDto<?> appleLogin(AppleLoginDto appleLoginDto) {
        Date now = new Date();
        System.out.println(appleLoginDto.getId());
        if (appleLoginDto.getId().isEmpty()) {
            return ResponseDto.fail(400, "error", "param error");
        }
        Optional<Member> member = Optional.ofNullable(memberRepository.findByProviderId("apple", appleLoginDto.getId()));

        if (!member.isEmpty() && member.get().getDeleted_at() != null) {
            return ResponseDto.fail(400, "error", "leave member");
        }
        boolean isCreate = false;

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .provider("apple")
                    .provider_id(appleLoginDto.getId())
                    .email(appleLoginDto.getEmail())
                    .name(appleLoginDto.getName())
                    .nickname(appleLoginDto.getName())
                    .profile_image(appleLoginDto.getProfileImage())
                    .build();
            memberRepository.save(newMember);

            member = Optional.of(newMember);
            isCreate = true;
        }

        String accessToken = jwtProvider.getAccessToken(member.get().getId());

        Long memberTermCount = memberTermRepository.countMemberTerm(member.get().getId());

        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", accessToken);
        result.put("member", member);
        result.put("isCreate", isCreate);
        result.put("termCount", memberTermCount);

        return ResponseDto.success(result);
    }


}
