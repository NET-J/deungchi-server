package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @GetMapping
    public ResponseDto<?> getMember(HttpServletRequest request) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.getMember(memberId);
    }

    @GetMapping("/all")
    public ResponseDto<?> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping
    public ResponseDto<?> update(HttpServletRequest request, @RequestBody MemberUpdateDto memberUpdateDto) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);

        return memberService.updateMember(memberId, memberUpdateDto);
    }
    @PostMapping("/profile")
    public ResponseDto<?> updateImage(HttpServletRequest request, @RequestBody(required = false) MultipartFile file) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.updateProfileImage(memberId, file);
    }

    @DeleteMapping("/leave")
    public ResponseDto<?> leaveMember(HttpServletRequest request, @RequestParam String reason) throws Exception{
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.leaveMember(memberId, reason);
    }

    @GetMapping("/resentKeyword")
    public ResponseDto<?> getResentKeyword(HttpServletRequest request) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.getResentKeyword(memberId);
    }

    @PostMapping("/request")
    public ResponseDto<?> postMemberRequestKeyword(HttpServletRequest request, @RequestParam String keyword) throws Exception{
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.postMemberRequestKeyword(memberId, keyword);
    }

    @GetMapping("/mypage")
    public ResponseDto<?> getMypage(HttpServletRequest request) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.getMypage(memberId);
    }

    @GetMapping("/badge")
    public ResponseDto<?> getMemberBadge(HttpServletRequest request) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.getMemberBadge(memberId);
    }

    @GetMapping("/badge/{badgeId}")
    public ResponseDto<?> getMemberBadge(HttpServletRequest request,@PathVariable Long badgeId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return memberService.getMemberBadgeDetail(memberId, badgeId);
    }
}
