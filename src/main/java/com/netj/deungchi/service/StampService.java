package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.repository.*;
import jakarta.persistence.EntityManager;
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
    private final EntityManager em;
    private final BadgeService badgeService;
    private static final String DEFAULT_IMAGE = "https://deungchi-prd.s3.ap-northeast-2.amazonaws.com/stamp/default/Stamp_img.png";

    public void postStamp(Long memberId, Long recordId) {
        Record record = recordRepository.findById(recordId).get();
        Member member = memberRepository.findById(memberId).get();
        Long mountainId = record.getMountain().getId();

        Stamp stamp = Stamp.builder().member(member).mountain(record.getMountain()).record(record).featuredImage(DEFAULT_IMAGE).build();

        stampRepository.save(stamp);

        List<Stamp> stampList =
                stampRepository.findAllByMemberIdAndMountainId(memberId, mountainId);
        Integer index = stampList.size();

        if ((index - 1) % 6 == 0) { // 6으로 나눈 나머지가 0이 되는 경우 (1, 7, 13, ...)
            badgeService.postMemberBadge(memberId, recordId);
        }
    }

    public void updateStampImage(Long memberId, Long recordId, String featuredImage) {
        Long stampId = stampRepository.findByMemberIdAndRecordId(memberId, recordId).getId();
        Stamp stamp = em.find(Stamp.class, stampId);

        // featuredImage가 null이면 기본 이미지를 사용하도록 설정
        stamp.setFeaturedImage(featuredImage != null ? featuredImage : DEFAULT_IMAGE);

        stampRepository.save(stamp);
    }

}
