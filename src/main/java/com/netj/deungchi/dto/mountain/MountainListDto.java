package com.netj.deungchi.dto.mountain;

import com.netj.deungchi.domain.Mountain;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MountainListDto {
    private String name;
    private String featuredImage;
    private String location;
    private String level;
    private String averageTime;
    private String altitude;
//    private Boolean isBookmark;

    public MountainListDto(Mountain mountain){
        this.name = mountain.getName();
        this.featuredImage = mountain.getFeatured_image();
        this.location = mountain.getLocation();
        this.level = mountain.getLevel();
        this.averageTime = mountain.getAverage_time();
        this.altitude = mountain.getAltitude();
    }
}
