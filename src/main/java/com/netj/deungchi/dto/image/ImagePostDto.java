package com.netj.deungchi.dto.image;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImagePostDto {
    private String name;
    private Long size;
    private String url;

    @Builder
    public ImagePostDto (String name, Long size, String url) {
        this.name = name;
        this.size = size;
        this.url = url;
    }
}
