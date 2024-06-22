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
    private final ImageRepository imageRepository;
    private final EntityManager em;
    private final StampService stampService;
    private final StampRepository stampRepository;
    private final MountainLandmarkRepository mountainLandmarkRepository;

    public ResponseDto<?> getRecord(Long recordId) {

        Optional<Record> record = recordRepository.findById(recordId);

        if(record.isEmpty()) {
            log.info("기록이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Record not found", "기록이 존재하지 않습니다.");
        }

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.get().getClass().getSimpleName(), record.get().getId());

        List<ImageUrlListResDto> imageUrlListResDtoList = imageList.stream().map(ImageUrlListResDto::new).toList();

        Stamp stamp = stampRepository.findByMemberIdAndRecordId(record.get().getMember().getId(), record.get().getId());
        StampResDto stampResDto = null;
        if (stamp != null) {
            stampResDto = new StampResDto(stamp);
        }

        RecordDetailResDto recordDetailResDto = RecordDetailResDto.builder().record(record.get()).build();
        recordDetailResDto.setImageList(imageUrlListResDtoList);
        recordDetailResDto.setStamp(stampResDto);

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
        record.setHikingDuration(recordPostReqDto.getHikingDuration());
        record.setHikingLength(recordPostReqDto.getHikingLength());

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

                List<Image> imageList = imageRepository.findAllByTableNameAndTableId("Record", recordId);

                if (!imageList.isEmpty()) {
                    stampService.updateStampImage(record.getMember().getId(),record.getId(), String.valueOf(imageList.get(0).getUrl()));
                } else {
                    stampService.updateStampImage(record.getMember().getId(),record.getId(), null);
                }
            }

        return ResponseDto.success("수정되었습니다.");
    }

    public ResponseDto<?> postRecordImages(Long recordId, List<ImagePostDto> imagePostDtoList) {
        Record record = recordRepository.findById(recordId).orElse(null);

        if (record == null) {
            return ResponseDto.fail(404, "Record not found", "기록이 존재하지 않습니다.");
        }

        recordRepository.save(record);

        if (imagePostDtoList != null && !imagePostDtoList.isEmpty()) {
            String featuredImageUrl = imagePostDtoList.get(0).getUrl();
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
            stampService.updateStampImage(record.getMember().getId(), recordId, featuredImageUrl);
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

        if(record.isEmpty()){
            return ResponseDto.fail(404, "Record not found", "등산하는 곳이 설정되지 않습니다.");
        } else {
            List<MountainLandmark> mountainLandmarkList = mountainLandmarkRepository.findAllByMountainId(record.get().getMountain().getId());

            return ResponseDto.success(mountainLandmarkList);
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

    public ResponseDto<?> postEndLocation(Long recordId, Long mountainLandmarkId) {

        Optional<MountainLandmark> mountainLandmark = mountainLandmarkRepository.findById(mountainLandmarkId);

        if(mountainLandmark.isEmpty()){
            return ResponseDto.fail(404, "Mountain Landmark not found", "해당 장소가 존재하지 않습니다.");
        } else {
            Optional<Mountain> mountain = mountainRepository.findById(mountainLandmark.get().getMountain().getId());
            Record record = em.find(Record.class, recordId);

            record.setMountain(mountain.get());
            record.setMountainLandmark(mountainLandmark.get());
            record.setEndLocation(mountainLandmark.get().getName());

            recordRepository.save(record);

            return ResponseDto.success("목적지가 설정되었습니다.");
        }
    }

    public Boolean hasStamp(Long recordId, Long memberId) {
        Stamp stamp = stampRepository.findByMemberIdAndRecordId(memberId, recordId);
        return stamp != null;
    }
}
