package com.netj.deungchi.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.course.CourseListResDto;
import com.netj.deungchi.dto.mountain.MountainListResDto;
import com.netj.deungchi.dto.record.RecordListResDto;
import com.netj.deungchi.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MountainService {

    public final MountainRepository mountainRepository;
    public final MemberRepository memberRepository;
    public final MemberSearchKeywordRepository memberSearchKeywordRepository;
    public final BookmarkRepository bookmarkRepository;
    public final RecommendedSearchKeywordRepository RecommendedSearchKeywordRepository;
    public final RecordRepository recordRepository;
    public final CourseRepository CourseRepository;
    public final ImageRepository imageRepository;

    public ResponseDto<?> getMountainList(Long memberId) {

        List<Mountain> mountains = mountainRepository.findAll();

        List< MountainListResDto > result = mountains.stream().map(mountain ->  new MountainListResDto(mountain, bookmarkRepository, memberId)).collect(Collectors.toList());

        return ResponseDto.success(result);
    }

    public ResponseDto<?> getRecommendedSearchKeyword() {

        List<RecommendedSearchKeyword> recommendedSearchKeywords = RecommendedSearchKeywordRepository.findAllByIsShow(true);
        List<String> result = recommendedSearchKeywords.stream().limit(5).map(RecommendedSearchKeyword::getSearchKeyword).collect(Collectors.toList());

        return ResponseDto.success(result);
    }

    public ResponseDto<?> getMountainsBySearch(Long memberId, String keyword) {

        this.postMountainSearchKeyword(memberId, keyword);

        List<Mountain> mountainsFindByName = mountainRepository.findByNameLike("%" + keyword + "%");

        List< MountainListResDto > resultByName = mountainsFindByName.stream().map(mountain ->  new MountainListResDto(mountain, bookmarkRepository, memberId)).collect(Collectors.toList());

        List<Mountain> mountainsFindByLocation = mountainRepository.findByLocationLike("%" + keyword + "%");

        List< MountainListResDto > resultByLocation = mountainsFindByLocation.stream().map(mountain ->  new MountainListResDto(mountain, bookmarkRepository, memberId)).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("name", resultByName);
        result.put("location", resultByLocation);

        return ResponseDto.success(result);
    }

    public void postMountainSearchKeyword(Long memberId, String keyword) {
        Optional<Member> member = memberRepository.findById(memberId);

        if(member.isEmpty()) {
            log.info(String.format("ID[%s] not found\",memberId)"));
            throw new NotFoundException(String.format("ID[%s] not found\",memberId)"));
        }
        MemberSearchKeyword memberSearchKeyword = MemberSearchKeyword.builder()
                .search_keyword(keyword)
                .member(member.get())
                .build();

        memberSearchKeywordRepository.save(memberSearchKeyword);
    }

    public ResponseDto<?> getMountainDetail(Long memberId, Long mountainId) {
        Optional<Mountain> mountain = mountainRepository.findById(mountainId);

        if(mountain.isEmpty()) {
            log.info("산이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Mountain not found", "산이 존재하지 않습니다.");
        }

        MountainListResDto mountainListResDto = MountainListResDto.builder().mountain(mountain.get()).bookmarkRepository(bookmarkRepository).memberId(memberId).build();

        List<Record> recordList = recordRepository.findRecordsByMountainId(mountainId);

        List<RecordListResDto> recordListResDtoList = recordList.stream()
                .limit(3)
                .map(record -> new RecordListResDto(record, imageRepository))
                .toList();


        List<Course> courseList = CourseRepository.findAllByMountain(mountain.get());

        List<CourseListResDto> courseListResDtoList = courseList.stream()
                .limit(3)
                .map(course -> new CourseListResDto(course, imageRepository))
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("mountain", mountainListResDto);
        result.put("recordList", recordListResDtoList);
        result.put("courseList", courseListResDtoList);

        return ResponseDto.success(result);
    }

    public ResponseDto<?> getRecordList(Long mountainId) {
        List<Record> recordList = recordRepository.findRecordsByMountainId(mountainId);

        List<RecordListResDto> recordListResDtoList = recordList.stream()
                .map(record -> new RecordListResDto(record, imageRepository))
                .toList();

        return ResponseDto.success(recordListResDtoList);

    }
}
