package com.netj.deungchi.dto.stamp;

import com.netj.deungchi.domain.Stamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StampResDto {
    private Long id;
    private String name;
    private String featureImage;
    private Timestamp createdAt;

    @Builder
    public StampResDto(Stamp stamp){
        this.id = stamp.getId();
        this.featureImage = stamp.getFeaturedImage();
        this.createdAt = stamp.getCreatedAt();
    }

}
