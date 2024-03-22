package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.domain.*;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.memberLocation.MemberLocationReqDto;
import com.netj.deungchi.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
    private final StampService stampService;

    public ResponseDto<?> postMemberLocation(Long memberId, MemberLocationReqDto memberLocationReqDto) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Record> record = recordRepository.findById(memberLocationReqDto.getRecordId());

        MemberLocation memberLocation = MemberLocation.builder().member(member.get()).record(record.get()).latitude(memberLocationReqDto.getLatitude()).longitude(memberLocationReqDto.getLongitude()).build();
        memberLocationRepository.save(memberLocation);

        Double totalDistance = calculateMemberTotalDistance(memberId, memberLocationReqDto.getRecordId());

        if(isEndLocation(memberLocation.getRecord().getId())){
            Optional<CourseDetail> courseDetail = courseDetailRepository.findById(record.get().getCourseDetail().getId());

            double distanceByEndLocation = geoUtils.calculateDistance(memberLocation.getLatitude(), memberLocation.getLongitude(), courseDetail.get().getLatitude(), courseDetail.get().getLongitude());
            MemberLocation memberLocationUpdate = em.find(MemberLocation.class, memberLocation.getId());
            memberLocationUpdate.setDistance(distanceByEndLocation);
            memberLocationRepository.save(memberLocation);

            if(distanceByEndLocation < 50) {
                stampService.postMemberStamp(memberId, record.get().getId());
                return ResponseDto.fail(201, "End Record", "목적지에 도착했습니다.");
            }
        }

        return ResponseDto.success(Collections.singletonMap("hikingLength", totalDistance));
    }

    public boolean isEndLocation(Long recordId) {
        Optional<Record> recordOptional = recordRepository.findById(recordId);
        if (recordOptional.isPresent()) {
            Record record = recordOptional.get();
            return record.getCourseDetail() != null;
        }
        return false; // 레코드가 존재하지 않는 경우
    }

    public Double calculateMemberTotalDistance(Long memberId, Long recordId) {
        List<MemberLocation> memberLocationList = memberLocationRepository.findAllByMemberIdAndRecordIdOrderByIdAsc(memberId, recordId);
        double totalDistance = 0.0; // 총 이동 거리를 저장할 변수 초기화

        // 이전 위치를 저장할 변수 초기화
        double prevLat = 0.0;
        double prevLon = 0.0;

        for(MemberLocation location : memberLocationList) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();

            // 첫 번째 위치인 경우 이전 위치를 현재 위치로 설정하고 건너뜀
            if (prevLat == 0.0 && prevLon == 0.0) {
                prevLat = lat;
                prevLon = lon;
                continue;
            }

            totalDistance += geoUtils.calculateDistance(prevLat, prevLon, lat, lon);

            // 현재 위치를 이전 위치로 설정
            prevLat = lat;
            prevLon = lon;
        }

//        km로 계산
        return totalDistance/1000;
    }


}
