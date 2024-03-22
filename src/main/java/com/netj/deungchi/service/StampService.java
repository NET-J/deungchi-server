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
public class StampService {

    public final RecordRepository recordRepository;
    public final StampRepository stampRepository;
    public final MemberRepository memberRepository;
    private final MemberStampRepository memberStampRepository;
    private final BadgeService badgeService;

    public void postMemberStamp(Long memberId, Long recordId) {
        Record record = recordRepository.findById(recordId).get();
        Long mountainId = record.getMountain().getId();

        List<Stamp> stampList = stampRepository.findAllByMountainIdOrderByIdAsc(mountainId);

        List<MemberStamp> memberStampList =
                memberStampRepository.getMemberStampByMemberIdAndMountainId(memberId, mountainId);
        Integer index = memberStampList.size();

        Stamp stamp = stampList.get(index);

        Member member = memberRepository.findById(memberId).get();

        MemberStamp memberStamp = MemberStamp.builder().member(member).record(record).mountain(record.getMountain()).stamp(stamp).build();

        memberStampRepository.save(memberStamp);

        if(index > 0 && (index % 5) == 0) { // 5의 배수인 경우에만 호출
            badgeService.postMemberBadge(memberId, recordId);
        }
    }

}
