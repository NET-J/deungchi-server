package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Record;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class RecordSimpleResDto {
    private Long memberId;
    private String content;
    private String level;
    private Date createdAt;

    @Builder
    public RecordSimpleResDto(Record record){
        log.info(String.valueOf(record.getId()));
        this.memberId = record.getMember().getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();
    }

}
