package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.record.RecordPostResDto;
import com.netj.deungchi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {

    public final RecordRepository recordRepository;
    private final MemberRepository memberRepository;
    private final MountainRepository mountainRepository;
    private final CourseRepository courseRepository;
    private final ImageRepository imageRepository;

    public ResponseDto<?> getRecord(Long id) {

        Optional<Record> record = recordRepository.findById(id);

        if(record.isEmpty()) {
            log.info("기록이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Record not found", "기록이 존재하지 않습니다.");
        }

        return ResponseDto.success(record.get());
    }

    public ResponseDto<?> getRecordList() {

        List<Record> records = recordRepository.findAll();

        return ResponseDto.success(records);
    }

    public ResponseDto<?> postRecord(RecordPostReqDto recordPostReqDto, Long memberId, List<String> imgPaths) {
        Optional<Member> member = memberRepository.findById(memberId);
        Optional<Mountain> mountain = mountainRepository.findById(recordPostReqDto.getMountainId());        Optional<Course> course = courseRepository.findById(recordPostReqDto.getCourseId());

        recordImgBlankCheck(imgPaths);

        if(member.isPresent()) {
            Record record = recordPostReqDto.toRecordEntity(member.get(), mountain.get(), course.get());
            recordRepository.save(record);

            List<String> imgList = new ArrayList<>();
            for (String imgUrl : imgPaths) {
                Image img = Image.builder()
                        .imageUrl(imgUrl)
                        .record(record)
                        .build();

                imageRepository.save(img);
                imgList.add(img.getImageUrl());
            }

            return ResponseDto.success(RecordPostResDto.of(record));
        } else {
            log.info("유저가 존재하지 않습니다.");
            return ResponseDto.fail(404, "Member not found", "유저가 존재하지 않습니다.");
        }
    }

    private void recordImgBlankCheck(List<String> imgPaths) {
        if(imgPaths == null || imgPaths.isEmpty()){
            throw new IllegalArgumentException("이미지 경로가 없습니다. 적어도 하나 이상의 이미지가 필요합니다.");
        }
    }

}
