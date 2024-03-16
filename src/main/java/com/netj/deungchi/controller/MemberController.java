package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseDto<?> getMember(@RequestParam Long id) {
        return memberService.getMember(id);
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
    public ResponseDto<?> updateImage(@RequestParam Long id, @RequestParam MultipartFile profileImage) throws IOException {
        return memberService.updateProfileImage(id, profileImage);
    }

    @DeleteMapping("/leave")
    public ResponseDto<?> leaveMember(@RequestParam Long memberId, @RequestParam String reason){
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
}
