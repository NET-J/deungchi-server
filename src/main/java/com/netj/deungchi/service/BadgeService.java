package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BadgeService {

    public final RecordRepository recordRepository;
    public final BadgeRepository badgeRepository;
    public final MemberRepository memberRepository;
    private final MemberBadgeRepository memberBadgeRepository;

    public void postMemberBadge(Long memberId, Long recordId) {
        Record record = recordRepository.findById(recordId).get();
        Long mountainId = record.getMountain().getId();

        List<Badge> badgeList = badgeRepository.findAllByMountainIdOrderByIdAsc(mountainId);
        List<MemberBadge> memberBadgeList =
                memberBadgeRepository.getMemberStampByMemberIdAndMountainId(memberId, mountainId);
        Integer index = memberBadgeList.size();

        Badge badge = badgeList.get(index);

        Member member = memberRepository.findById(memberId).get();
        
        MemberBadge memberBadge = MemberBadge.builder().member(member).record(record).mountain(record.getMountain()).badge(badge).build();

        memberBadgeRepository.save(memberBadge);
    }

}
