package com.netj.deungchi.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.util.StringUtils;
import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.member.MemberUpdateDto;
import com.netj.deungchi.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    public final MemberRepository memberRepository;
    public final MemberLeaveRepository memberLeaveRepository;
    public final MemberSearchKeywordRepository memberSearchKeywordRepository;
    public final MemberRequestKeywordRepository memberRequestKeywordRepository;
    public final RecordRepository recordRepository;
    public final BadgeRepository badgeRepository;
    public final StampRepository stampRepository;

    @Autowired
    private final S3Uploader s3Uploader;


    public ResponseDto<?> getMember(Long id) {
        return ResponseDto.success(memberRepository.findById(id));
    }

    public ResponseDto<?> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return ResponseDto.success(members);
    }

    public ResponseDto<?> updateMember(Long memberId, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);

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
        if (profileImage != null) {
            String storedFileName = s3Uploader.upload(profileImage,"member/profileImages");
            member.setProfile_image(storedFileName);
        }


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

        return ResponseDto.success("회원 탈퇴 완료");
    }

    public ResponseDto<?> getResentKeyword(Long memberId) {

        Optional<Member> member = memberRepository.findById(memberId);

        if(member.isEmpty()) {
            log.info(String.format("ID[%s] not found\",memberId)"));
            throw new NotFoundException(String.format("ID[%s] not found\",memberId)"));
        }

        List<MemberSearchKeyword> resentKeywordList = memberSearchKeywordRepository.getMemberSearchKeywordsByMember_Id(memberId);
        List<String> result = resentKeywordList.stream().limit(5).map(MemberSearchKeyword::getSearch_keyword).collect(Collectors.toList());

        return ResponseDto.success(result);
    }

    public ResponseDto<?> postMemberRequestKeyword(Long memberId, String keyword){
        Optional<Member> member = memberRepository.findById(memberId);

        if(member.isEmpty()) {
            log.info(String.format("ID[%s] not found\",memberId)"));
            throw new NotFoundException(String.format("ID[%s] not found\",memberId)"));
        }

        MemberRequestKeyword memberRequestKeyword = MemberRequestKeyword.builder()
                .request_keyword(keyword)
                .member(member.get())
                .build();

        memberRequestKeywordRepository.save(memberRequestKeyword);

        return ResponseDto.success("검색어 요청 완료");
    }

    public ResponseDto<?> getMypage(Long memberId){
        Member member = memberRepository.findById(memberId).get();

        List<Record> records = recordRepository.getMemberRecord(memberId);
        List<Badge> badges = badgeRepository.getMemberBadge(memberId);

        Map<String, Object> result = new HashMap<>();
        result.put("member", member);
        result.put("recordCount", records.size());
        result.put("badgeCount", badges.size());

        List<Record> subRecord;
        if (records.size() > 3) {
            subRecord = records.subList(0, 2);
        } else {
            subRecord = records;
        }

        List<Badge> subBadge;
        if (badges.size() > 3) {
            subBadge = badges.subList(0, 2);
        } else {
            subBadge = badges;
        }


        result.put("records", subRecord);
        result.put("badges", subBadge);

//        result.put("badgeCount", badgeCount);
        return ResponseDto.success(result);
    }

    public ResponseDto<?> getMemberBadge(Long memberId){
//        Member member = memberRepository.findById(memberId).get();

        List<Badge> collectBadges = badgeRepository.getMemberBadge(memberId);
        List<Badge> notCollectBadges = badgeRepository.getNotMemberBadge(memberId);

        Map<String, Object> result = new HashMap<>();
        result.put("collectBadges", collectBadges);
        result.put("notCollectBadges", notCollectBadges);

        return ResponseDto.success(result);
    }

    public ResponseDto<?> getMemberBadgeDetail(Long memberId, Long BadgeId){

        Badge badge = badgeRepository.getMemberBadgeBadgeId(memberId, BadgeId);
        List<Stamp> memberStamps = stampRepository.findAllByMemberIdAndMountainId(memberId, Long.parseLong(String.valueOf(badge.getMountain().getId())));

        Map<String, Object> result = new HashMap<>();
        result.put("badge", badge);
        result.put("memberStamps", memberStamps);

        return ResponseDto.success(result);
    }


    public ResponseDto<?> getMemberRecord(Long memberId, String sortBy){

        List<Record> records = recordRepository.getMemberRecord(memberId);

        Map<String, List<Record>> recordMonthly;
        if (!records.isEmpty()) {
            recordMonthly = records.stream().collect(groupingBy(item -> item.getCreatedAt().toString().substring(0, 10)));
            if (sortBy.isEmpty() || !sortBy.equals("asc") ) {
                List<String> keySet = new ArrayList<>(recordMonthly.keySet());
                Map<String, List<Record>> recordMonthlySort = new LinkedHashMap<>();

                // 키 값으로 오름차순 정렬
                Collections.reverse(keySet);

                for (String key : keySet) {
                    recordMonthlySort.put(key, recordMonthly.get(key));
                }
                recordMonthly = recordMonthlySort;
            }
        } else {
            recordMonthly = new HashMap<>();
        }

        return ResponseDto.success(recordMonthly);
    }
}
