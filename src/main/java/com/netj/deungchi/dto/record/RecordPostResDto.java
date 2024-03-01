package com.netj.deungchi.dto.record;

import lombok.Builder;

import java.sql.Timestamp;
import com.netj.deungchi.domain.Record;

@Builder
public class RecordPostResDto {
    private String mountainName;
    private String endLocation;
    private String hikingDuration;
    private Float hikingLength;
    private Timestamp createdAt;

    public static RecordPostResDto of(Record record) {
        String mountainName = record.getMountain().getName();
        return RecordPostResDto.builder()
                .mountainName(mountainName)
                .endLocation(record.getEndLocation())
                .hikingDuration(record.getHikingDuration()).hikingLength(record.getHikingLength())
                .createdAt(record.getCreatedAt())
                .build();
    }
}
