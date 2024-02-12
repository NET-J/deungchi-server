package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class RecordPublicDto {
    private Long memberId;
    private String content;
    private Integer level;
    private Date createdAt;

    @Builder
    public RecordPublicDto(Record record){
        this.memberId = record.getMember().getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();
    }

}
