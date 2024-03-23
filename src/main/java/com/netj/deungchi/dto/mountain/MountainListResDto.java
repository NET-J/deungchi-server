package com.netj.deungchi.dto.mountain;

import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.repository.BookmarkRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MountainListResDto {
    private Long id;
    private String name;
    private String featuredImage;
    private String location;
    private String level;
    private String averageTime;
    private String altitude;
    private Double latitude;
    private Double longitude;
    private Boolean isBookmark;

    @Builder
    public MountainListResDto(Mountain mountain, BookmarkRepository bookmarkRepository, Long memberId){
        this.id = mountain.getId();
        this.name = mountain.getName();
        this.featuredImage = mountain.getFeatured_image();
        this.location = mountain.getLocation();
        this.level = mountain.getLevel();
        this.averageTime = mountain.getAverage_time();
        this.altitude = mountain.getAltitude();
        this.latitude = mountain.getLatitude();
        this.longitude = mountain.getLongitude();
        this.isBookmark = bookmarkRepository.findByMemberIdAndMountainId(memberId, this.id).isPresent();
    }
}
