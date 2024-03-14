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
public class RecordSimpleResDto {
    private Long memberId;
    private String content;
    private String level;
    private Date createdAt;

    @Builder
    public RecordSimpleResDto(Record record){
        this.memberId = record.getMember().getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();
    }

}
