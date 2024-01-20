package com.netj.deungchi.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.StringUtils;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.MetaMessage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    public final MemberRepository memberRepository;
    @Autowired
    private final S3Uploader s3Uploader;


    public ResponseDto<?> getMember(Long id) {
        return ResponseDto.success(memberRepository.findById(id));
    }

    public ResponseDto<?> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return ResponseDto.success(members);
    }

    public ResponseDto<?> updateMember(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(Long.valueOf(memberUpdateDto.getId())).orElseThrow(IllegalArgumentException::new);

        if(!StringUtils.isNullOrEmpty(memberUpdateDto.getEmail())) {
            member.setEmail(memberUpdateDto.getEmail());
        }
        if(!StringUtils.isNullOrEmpty(memberUpdateDto.getName())) {
            member.setName(memberUpdateDto.getName());
        }
        if(!StringUtils.isNullOrEmpty(memberUpdateDto.getNickname())) {
            member.setNickname(memberUpdateDto.getNickname());
        }
        if(!StringUtils.isNullOrEmpty(memberUpdateDto.getPhone())) {
            member.setPhone(memberUpdateDto.getPhone());
        }

        return ResponseDto.success(member);
    }

    public ResponseDto<?> updateProfileImage(Long id, MultipartFile profileImage) throws IOException {
        Member member = memberRepository.findById(id).get();
        String storedFileName = s3Uploader.upload(profileImage,"member/profileImages");

        member.setProfile_image(storedFileName);

        return ResponseDto.success(member);
    }
}
