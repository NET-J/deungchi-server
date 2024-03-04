package com.netj.deungchi.dto.mountain;

import com.netj.deungchi.domain.Mountain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MountainDto {
    private String name;
    private String featuredImage;
    private String location;
    private String level;
    private String averageTime;
    private String altitude;
    private Float latitude;
    private Float longitude;
//    private Boolean isBookmark;

    @Builder
    public MountainDto(Mountain mountain){
        this.name = mountain.getName();
        this.featuredImage = mountain.getFeatured_image();
        this.location = mountain.getLocation();
        this.level = mountain.getLevel();
        this.averageTime = mountain.getAverage_time();
        this.altitude = mountain.getAltitude();
        this.latitude = mountain.getLatitude();
        this.longitude = mountain.getLongitude();
    }
}
