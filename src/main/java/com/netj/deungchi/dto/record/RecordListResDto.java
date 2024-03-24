package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Image;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.dto.member.MemberDto;
import com.netj.deungchi.repository.ImageRepository;
import com.netj.deungchi.repository.RecordGoodRepository;
import com.netj.deungchi.repository.RecordLikeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecordListResDto {
    private Long id;
    private MemberDto member;
    private String content;
    private String level;
    private List<ImageUrlListResDto> imageList;
    private Integer likeCount;
    private Integer goodCount;
    private Date createdAt;

    @Builder
    public RecordListResDto(Record record, ImageRepository imageRepository, RecordLikeRepository recordLikeRepository, RecordGoodRepository recordGoodRepository){
        Member member = record.getMember();
        this.member = MemberDto.builder().member(member).build();

        this.id = record.getId();
        this.content = record.getContent();
        this.level = record.getLevel();
        this.createdAt = record.getCreatedAt();

        this.likeCount = recordLikeRepository.countByRecordId(record.getId());
        this.goodCount = recordGoodRepository.countByRecordId(record.getId());

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(record.getClass().getSimpleName(), record.getId());
        this.imageList = imageList.stream()
                .map(image -> new ImageUrlListResDto(image))
                .toList();
    }

}
