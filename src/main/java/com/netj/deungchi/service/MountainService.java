package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.mountain.MountainListResDto;
import com.netj.deungchi.dto.record.RecordSimpleResDto;
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
    public final BookmarkRepository bookmarkRepository;
    public final RecommendedSearchKeywordRepository RecommendedSearchKeywordRepository;
    public final RecordRepository RecordRepository;
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
        List<Mountain> mountains = mountainRepository.findByNameLike("%" + keyword + "%");

        List< MountainListResDto > result = mountains.stream().map(mountain ->  new MountainListResDto(mountain, bookmarkRepository, memberId)).collect(Collectors.toList());

        return ResponseDto.success(result);
    }

    public ResponseDto<?> getMountainDetail(Long mountainId) {
        Optional<Mountain> mountain = mountainRepository.findById(mountainId);

        if(mountain.isEmpty()) {
            log.info("산이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Mountain not found", "산이 존재하지 않습니다.");
        }

        MountainListResDto mountainDto = MountainListResDto.builder().mountain(mountain.get()).build();

        List<Record> recordList = RecordRepository.findAll();

        List<RecordSimpleResDto> recordListResDtoList = recordList.stream()
                .limit(3)
                .map(record -> new RecordSimpleResDto(record, imageRepository))
                .toList();


        List<Course> courseList = CourseRepository.findAllByMountain(mountain.get());

        Map<String, Object> result = new HashMap<>();
        result.put("mountain", mountainDto);
        result.put("reviewList", recordListResDtoList);
        result.put("courseList", courseList);

        return ResponseDto.success(result);
    }
}
