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
public class MountainCooridateDto {
    private Long id;
    private String name;
    private Float latitude;
    private Float longitude;

    @Builder
    public MountainCooridateDto(Mountain mountain){
        this.id = mountain.getId();
        this.name = mountain.getName();
        this.latitude = mountain.getLatitude();
        this.longitude = mountain.getLongitude();
    }
}
