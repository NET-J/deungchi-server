package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.domain.Mountain;
import lombok.Getter;

@Getter
public class RecordPostReqDto {
    private Long mountainId;
    private Long courseId;
    private String endLocation;
    private String level;
    private String content;
    private Boolean isShare;
    private String hikingDuration;
    private Float hikingLength;

    public Record toRecordEntity(Member member, Mountain mountain, Course course) {
        return Record.builder()
                .member(member)
                .mountain(mountain)
                .course(course)
                .level(this.level)
                .content(this.content)
                .endLocation(this.endLocation)
                .hikingLength(this.hikingLength)
                .hikingDuration(this.hikingDuration)
                .isShare(this.isShare)
                .build();
    }
}
