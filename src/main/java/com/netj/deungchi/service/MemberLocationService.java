package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.domain.*;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.memberLocation.MemberLocationReqDto;
import com.netj.deungchi.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberLocationService {

    public final RecordRepository recordRepository;
    public final MemberRepository memberRepository;
    public final MemberLocationRepository memberLocationRepository;
    public final GeoUtils geoUtils;
    public final CourseDetailRepository courseDetailRepository;
    private final EntityManager em;

    public ResponseDto<?> postMemberLocation(Long memberId, MemberLocationReqDto memberLocationReqDto) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Record> record = recordRepository.findById(memberLocationReqDto.getRecordId());

        MemberLocation memberLocation = MemberLocation.builder().member(member.get()).record(record.get()).latitude(memberLocationReqDto.getLatitude()).longitude(memberLocationReqDto.getLongitude()).build();
        memberLocationRepository.save(memberLocation);

        if(isEndLocation(memberLocation.getRecord().getId())){
            Optional<CourseDetail> courseDetail = courseDetailRepository.findById(record.get().getCourseDetail().getId());

            double distance = geoUtils.calculateDistance(memberLocation.getLatitude(), memberLocation.getLongitude(), courseDetail.get().getLatitude(), courseDetail.get().getLongitude());
            MemberLocation memberLocationUpdate = em.find(MemberLocation.class, memberLocation.getId());
            memberLocationUpdate.setDistance(distance);
            memberLocationRepository.save(memberLocation);

            if(distance < 50) {
                return ResponseDto.fail(201, "End Record", "목적지에 도착했습니다.");
            }
        }

        return ResponseDto.success("기록 중입니다.");
    }

    public boolean isEndLocation(Long recordId) {
        Optional<Record> recordOptional = recordRepository.findById(recordId);
        if (recordOptional.isPresent()) {
            Record record = recordOptional.get();
            return record.getCourseDetail() != null;
        }
        return false; // 레코드가 존재하지 않는 경우
    }


}
