package com.netj.deungchi.dto.mountain;

import com.netj.deungchi.domain.Mountain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MountainStartLocationResDto {
    private Long id;
    private String name;
    private String location;

    @Builder
    public MountainStartLocationResDto(Mountain mountain) {
        this.id = mountain.getId();
        this.name = mountain.getName();
        this.location = mountain.getLocation();
    }
}
