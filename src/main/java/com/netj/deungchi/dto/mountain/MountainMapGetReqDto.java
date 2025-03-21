package com.netj.deungchi.dto.mountain;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.domain.Record;
import lombok.Getter;

@Getter
public class MountainMapGetReqDto {
    private Long memberId;
    private Long courseId;
    private String endLocation;
    private String level;
    private String content;
    private Boolean isShare;
    private String hikingDuration;
    private Double hikingLength;

    public Record toRecordEntity(Member member, Mountain mountain) {
        return Record.builder()
                .member(member)
                .mountain(mountain)
                .level(this.level)
                .content(this.content)
                .endLocation(this.endLocation)
                .hikingLength(this.hikingLength)
                .hikingDuration(this.hikingDuration)
                .isShare(this.isShare)
                .build();
    }
}
