package com.netj.deungchi.dto.record;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.sql.Timestamp;
import com.netj.deungchi.domain.Record;


@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@AllArgsConstructor
public class RecordPostResDto {
    private Long id;
    private String mountainName;
    private String hikingDuration;
    private Double hikingLength;
    private Timestamp createdAt;

    public static RecordPostResDto of(Record record) {
        String mountainName = record.getMountain().getName();
        return RecordPostResDto.builder()
                .id(record.getId())
                .mountainName(mountainName)
                .hikingDuration(record.getHikingDuration())
                .hikingLength(record.getHikingLength())
                .createdAt(record.getCreatedAt())
                .build();
    }
}
