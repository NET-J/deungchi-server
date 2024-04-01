package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Image;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MountainRecordListResDto {
    private Long id;
    private String content;
    private String level;
    private List<ImageUrlListResDto> imageList;
    private Date createdAt;

    @Builder
    public MountainRecordListResDto(Record record, ImageRepository imageRepository){
        this.id = record.getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.getClass().getSimpleName(), record.getId());
        this.imageList = imageList.stream()
                .map(image -> new ImageUrlListResDto(image))
                .toList();
    }

}
