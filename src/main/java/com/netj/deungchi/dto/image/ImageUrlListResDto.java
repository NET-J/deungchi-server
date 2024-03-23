package com.netj.deungchi.dto.image;

import com.netj.deungchi.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ImageUrlListResDto {
    private Long id;
    private String name;
    private String url;

    @Builder
    public ImageUrlListResDto(Image image){
        this.id = image.getId();
        this.name = image.getName();
        this.url = image.getUrl();
    }

}
