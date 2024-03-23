package com.netj.deungchi.dto.stamp;

import com.netj.deungchi.domain.Stamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StampResDto {
    private Long id;
    private String name;
    private String featureImage;

    @Builder
    public StampResDto(Stamp stamp){
        this.id = stamp.getId();
        this.name = stamp.getName();
        this.featureImage = stamp.getFeatured_image();
    }

}
