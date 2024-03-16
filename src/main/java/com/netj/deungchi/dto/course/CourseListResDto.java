package com.netj.deungchi.dto.course;

import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Image;
import com.netj.deungchi.dto.image.ImageUrlListResDto;
import com.netj.deungchi.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CourseListResDto {
    private Long id;
    private String name;
    private String level;
    private String averageTime;
    private String distance;
    private String path;
    private String transportationInfo;
    private String parkingInfo;
    private List<ImageUrlListResDto> imageList;

    @Builder
    public CourseListResDto(Course course, ImageRepository imageRepository){
        this.id = course.getId();
        this.name = course.getName();
        this.level = course.getLevel();
        this.averageTime = course.getAverageTime();
        this.distance = course.getDistance();
        this.path = course.getPath();
        this.transportationInfo = course.getTransportationInfo();
        this.parkingInfo = course.getParkingInfo();

        List<Image> imageList = imageRepository.findAllByTableNameAndTableId(course.getClass().getSimpleName(), course.getId());
        this.imageList = imageList.stream()
                .map(image -> new ImageUrlListResDto(image))
                .toList();
    }
}
