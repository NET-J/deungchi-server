package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Image;
import com.netj.deungchi.domain.Record;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class RecordSimpleResDto {

    private Long memberId;
    private String content;
    private String level;
    private Date createdAt;
    private List<Image> imageList;

    @Builder
    public RecordSimpleResDto(Record record){
        this.memberId = record.getMember().getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();
    }
}
