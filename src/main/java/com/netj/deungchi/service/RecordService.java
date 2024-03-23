package com.netj.deungchi.service;

import com.netj.deungchi.domain.*;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImagePostDto;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.dto.mountain.MountainStartLocationResDto;
import com.netj.deungchi.dto.record.*;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.stamp.StampResDto;
import com.netj.deungchi.repository.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {

    public final RecordRepository recordRepository;
    private final MemberRepository memberRepository;
    private final MemberStampRepository memberStampRepository;
    private final MountainRepository mountainRepository;
    private final CourseRepository courseRepository;
    private final CourseDetailRepository courseDetailRepository;
    private final ImageRepository imageRepository;
    private final EntityManager em;
    private final BadgeRepository badgeRepository;

    public ResponseDto<?> getRecord(Long recordId) {

        Optional<Record> record = recordRepository.findById(recordId);

        if(record.isEmpty()) {
            log.info("기록이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Record not found", "기록이 존재하지 않습니다.");
        }

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.get().getClass().getSimpleName(), record.get().getId());

        List<ImageUrlListResDto> imageUrlListResDtoList = imageList.stream().map(ImageUrlListResDto::new).toList();

        MemberStamp memberStamp = memberStampRepository.findByMemberIdAndRecordId(record.get().getMember().getId(), record.get().getId());

        StampResDto stampResDto = null;
        if (memberStamp != null) {
            stampResDto = new StampResDto(memberStamp.getStamp());
        }

        RecordDetailResDto recordDetailResDto = RecordDetailResDto.builder().record(record.get()).build();
        recordDetailResDto.setImageList(imageUrlListResDtoList);
        recordDetailResDto.setStamp(stampResDto);

        List<Badge> badges = badgeRepository.getMemberBadgeByRecordId(recordDetailResDto.getId());
        recordDetailResDto.setBadges(badges);

//        Optional<Course> course = courseRepository.findById(recordDetailResDto.getCourse().getId());
//        recordDetailResDto.setCourse(recordDetailResDto.getCourse());

        return ResponseDto.success(recordDetailResDto);
    }

    public ResponseDto<?> getRecordList() {

        List<Record> records = recordRepository.findAll();

        return ResponseDto.success(records);
    }

    public ResponseDto<?> endRecord(Long recordId, RecordPostReqDto recordPostReqDto) {
        Record record = em.find(Record.class, recordId);

        record.setContent(recordPostReqDto.getContent());
        record.setLevel(recordPostReqDto.getLevel());
        record.setIsShare(recordPostReqDto.getIsShare());
        record.setTemperature(recordPostReqDto.getTemperature());
        record.setWeatherCode(recordPostReqDto.getWeatherCode());

        recordRepository.save(record);

        return ResponseDto.success(RecordPostResDto.of(record));
    }

        public ResponseDto<?> updateRecordDetail(Long recordId, RecordUpdateReqDto recordUpdateReqDto) {

        Record record = recordRepository.findById(recordId).get();

        record.setContent(recordUpdateReqDto.getContent());
        record.setLevel(recordUpdateReqDto.getLevel());
        record.setIsShare(recordUpdateReqDto.getIsShare());
        recordRepository.save(record);

            if (recordUpdateReqDto.getDeleteImages() != null && !recordUpdateReqDto.getDeleteImages().isEmpty())
            {
                List<Long> deleteImgLong = new ArrayList<>();

                String[] deleteImgs = recordUpdateReqDto.getDeleteImages().split(",");
                for (String deleteImg: deleteImgs) {
                    deleteImgLong.add(Long.parseLong(deleteImg));
                }

                imageRepository.deleteAllByIdInBatch(deleteImgLong);
            }


        return ResponseDto.success("수정되었습니다.");
    }

    public ResponseDto<?> postRecordImages(Long recordId, List<ImagePostDto> imagePostDtoList) {
        Record record = recordRepository.findById(recordId).get();

        recordRepository.save(record);

        if (imagePostDtoList != null) {
            for (ImagePostDto imagePostDto : imagePostDtoList) {
                Image img = Image.builder()
                        .url(imagePostDto.getUrl())
                        .name(imagePostDto.getName())
                        .size(imagePostDto.getSize())
                        .tableName(record.getClass().getSimpleName())
                        .tableId(record.getId())
                        .build();

                imageRepository.save(img);
            }
        }
        return ResponseDto.success(true);
    }

    public ResponseDto<?> getStartLocationBySearch(String keyword) {
        List<Mountain> mountainsFindByName = mountainRepository.findByNameLike("%" + keyword + "%");

        List <MountainStartLocationResDto> result = mountainsFindByName.stream().map(MountainStartLocationResDto::new).toList();

        return ResponseDto.success(result);
    }

    public ResponseDto<?> postStartLocation(Long memberId, Long mountainId) {
        Optional<Mountain> mountain = mountainRepository.findById(mountainId);
        if(mountain.isEmpty()){
            return ResponseDto.fail(404, "Mountain not found", "등산하는 곳이 존재하지 않습니다.");
        } else {
            Optional<Member> member = memberRepository.findById(memberId);
            Record record= Record.builder().mountain(mountain.get()).member(member.get()).build();
            recordRepository.save(record);

            return ResponseDto.success(record);
        }

    }

    public ResponseDto<?> getEndLocation(Long recordId) {
        Optional<Record> record = recordRepository.findById(recordId);
        log.info("recordService.getEndLocation");
        if(record.isEmpty()){
            return ResponseDto.fail(404, "Record not found", "등산하는 곳이 설정되지 않습니다.");
        } else {
            List<Course> courseList = courseRepository.findAllByMountainId(record.get().getMountain().getId());

            List<CourseDetail> courseDetailList = new ArrayList<>();

            for (Course course : courseList) {
                List<CourseDetail> courseDetails = courseDetailRepository.findAllByCourseId(course.getId());
                courseDetailList.addAll(courseDetails);
            }

            return ResponseDto.success(courseDetailList);

        }
    }

    public ResponseDto<?> updateStartLocation(Long recordId, Long mountainId) {
        Record record = em.find(Record.class, recordId);

        Optional<Mountain> mountain = mountainRepository.findById(mountainId);

        if(mountain.isEmpty()){
            return ResponseDto.fail(404, "Mountain not found", "등산하는 곳이 존재하지 않습니다.");
        } else {
            record.setMountain(mountain.get());
            recordRepository.save(record);

            return ResponseDto.success("등산하는 곳이 변경되었습니다.");
        }
    }

    public ResponseDto<?> postEndLocation(Long recordId, Long courseDetailId) {
        Optional<CourseDetail> courseDetail = courseDetailRepository.findById(courseDetailId);

        if(courseDetail.isEmpty()){
            return ResponseDto.fail(404, "Course not found", "해당 코스가 존재하지 않습니다.");
        } else {
            Optional<Course> course = courseRepository.findById(courseDetail.get().getCourse().getId());
            Record record = em.find(Record.class, recordId);

            record.setCourse(course.get());
            record.setCourseDetail(courseDetail.get());
            record.setEndLocation(courseDetail.get().getName());

            log.info(courseDetail.get().getName());

            recordRepository.save(record);

            return ResponseDto.success("목적지가 설정되었습니다.");
        }
    }

}
