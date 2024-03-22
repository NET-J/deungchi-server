package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
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
    public final MemberLocationRepository memberLocationRepository;
    private final MemberStampRepository memberStampRepository;
    public final GeoUtils geoUtils;
    public final CourseDetailRepository courseDetailRepository;
    private final EntityManager em;

    public void postMemberStamp(Long memberId, Long recordId) {
        Record record = recordRepository.findById(recordId).get();
        Long mountainId = record.getMountain().getId();

        List<Stamp> stampList = stampRepository.findAllByMountainIdOrderByIdAsc(mountainId);

        List<MemberStamp> memberStampList =
                memberStampRepository.getMemberStampByMemberIdAndMountainId(memberId, mountainId);
        Integer index = memberStampList.size();
        log.error(String.valueOf(index));
        Stamp stamp = stampList.get(index);

        Member member = memberRepository.findById(memberId).get();

        MemberStamp memberStamp = MemberStamp.builder().member(member).record(record).mountain(record.getMountain()).stamp(stamp).build();

        memberStampRepository.save(memberStamp);
    }

}
