package com.netj.deungchi.controller;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.repository.MemberRepository;
import com.netj.deungchi.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseDto<?> getMember(@RequestParam Long id) {
        return memberService.getMember(id);
    }

    @GetMapping("/all")
    public ResponseDto<?> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping
    public ResponseDto<?> update(@RequestBody MemberUpdateDto memberUpdateDto) {
        return memberService.updateMember(memberUpdateDto);
    }
    @PostMapping("/profile")
    public ResponseDto<?> updateImage(@RequestParam Long id, @RequestParam MultipartFile profileImage) throws IOException {
        return memberService.updateProfileImage(id, profileImage);
    }
}
