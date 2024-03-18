package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.memberLocation.MemberLocationReqDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.MemberLocationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
@Slf4j
public class MemberLocationController {

    private final JwtProvider jwtProvider;
    private final MemberLocationService memberLocationService;

    @PostMapping
    public ResponseDto<?> postMemberLocation(HttpServletRequest request, @RequestBody MemberLocationReqDto memberLocationReqDto) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberLocationService.postMemberLocation(memberId, memberLocationReqDto);
    }

}
