package com.netj.deungchi.dto.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netj.deungchi.domain.Badge;
import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Slf4j
public class RecordDetailResDto {

    private Long id;
    private Long course_id;
    private String mountainName;
    private String endLocation;
    private String level;
    private String hikingDuration;
    private Double hikingLength;
    private Integer temperature;
    private String content;
    private Boolean isShare;
    private List<ImageUrlListResDto> imageList;
    private Date createdAt;
    private List<Badge> badges;
    private Course course;

    @Builder
    public RecordDetailResDto(Record record){
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
        this.course = record.getCourse();
    }
}
