package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Image;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.repository.ImageRepository;
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
    private List<ImageUrlListResDto> imageList;

    @Builder
    public RecordSimpleResDto(Record record, ImageRepository imageRepository){
        this.memberId = record.getMember().getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.getClass().getSimpleName(), record.getId());
        this.imageList = imageList.stream()
                .map(image -> new ImageUrlListResDto(image))
                .toList();
    }
}
