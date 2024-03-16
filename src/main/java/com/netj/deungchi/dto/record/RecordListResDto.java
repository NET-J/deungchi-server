package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecordListResDto {
    private Long memberId;
    private String memberName;
    private String profileImage;
    private String content;
    private String level;
    private Date createdAt;

    @Builder
    public RecordListResDto(Record record){
        this.memberId = record.getMember().getId();
        this.memberName = record.getMember().getName();
        this.profileImage = record.getMember().getProfile_image();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();
    }

}
