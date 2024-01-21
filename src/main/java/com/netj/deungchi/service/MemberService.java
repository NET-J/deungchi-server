package com.netj.deungchi.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.StringUtils;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.MemberLeave;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.repository.MemberLeaveRepository;
import com.netj.deungchi.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.midi.MetaMessage;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    public final MemberRepository memberRepository;
    public final MemberLeaveRepository memberLeaveRepository;
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
        if(memberUpdateDto.getIsNotiEmail() != null) {
            member.setIs_noti_email(memberUpdateDto.getIsNotiEmail());
        }
        if(memberUpdateDto.getIsNotiSms() != null) {
            member.setIs_noti_sms(memberUpdateDto.getIsNotiSms());
        }
        if(memberUpdateDto.getIsNotiPush() != null) {
            member.setIs_noti_push(memberUpdateDto.getIsNotiPush());
        }

        return ResponseDto.success(member);
    }

    public ResponseDto<?> updateProfileImage(Long id, MultipartFile profileImage) throws IOException {
        Member member = memberRepository.findById(id).get();
        String storedFileName = s3Uploader.upload(profileImage,"member/profileImages");

        member.setProfile_image(storedFileName);

        return ResponseDto.success(member);
    }

    public ResponseDto<?> leaveMember(Long memberId, String reason) {

        // 현재 날짜 구하기
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        MemberLeave memberLeave = MemberLeave.builder()
                .member_id(memberId)
                .reason(reason)
                .created_at(date)
                .build();

        memberLeaveRepository.save(memberLeave);

        Member member = memberRepository.findById(memberId).get();
        member.setDeleted_at(date);

        return ResponseDto.success(null);
    }
}
