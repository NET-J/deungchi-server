package com.netj.deungchi.dto.record;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordPostReqDto {

    private String level;
    private String content;
    private Boolean isShare;
    private String hikingDuration;
    private Double hikingLength;
    private Integer temperature;
    private Integer weatherCode;
}
