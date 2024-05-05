package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Image;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.dto.member.MemberDto;
import com.netj.deungchi.repository.ImageRepository;
import com.netj.deungchi.repository.RecordGoodRepository;
import com.netj.deungchi.repository.RecordLikeRepository;
import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberRecordResDto {
    private Long id;
    private String mountainName;
    private String endLocation;
    private String level;
    private String hikingDuration;
    private Double hikingLength;
    private Integer temperature;
    private String content;
    private Boolean isShare;
    private List<ImageUrlListResDto> imageList;
    private Integer likeCount;
    private Integer goodCount;
    private Timestamp createdAt;

    @Builder
    public MemberRecordResDto(Record record, ImageRepository imageRepository){
        this.id = record.getId();
        this.mountainName = record.getMountain().getName();
        this.endLocation = record.getEndLocation();
        this.level = record.getLevel();
        this.hikingDuration = record.getHikingDuration();
        this.hikingLength = record.getHikingLength();
        this.temperature = record.getTemperature();
        this.content = record.getContent();
        this.isShare = record.getIsShare();
        this.createdAt = record.getCreatedAt();

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.getClass().getSimpleName(), record.getId());
        this.imageList = imageList.stream()
                .map(image -> new ImageUrlListResDto(image))
                .toList();
    }

}
