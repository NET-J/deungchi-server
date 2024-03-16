package com.netj.deungchi.dto.image;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImagePostDto {
    private final String name;
    private final Long size;
    private final String url;

    @Builder
    public ImagePostDto (String name, Long size, String url) {
        this.name = name;
        this.size = size;
        this.url = url;
    }
}
