package com.netj.deungchi.dto.record;

import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Mountain;
import lombok.Getter;

@Getter
public class RecordEndLocationDto {
    private Member member;
    private Mountain mountain;
    private Course course;
    private String endLocation;
}
