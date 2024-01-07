package com.netj.deungchi.dto;

import com.netj.deungchi.domain.Badge;
import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Mountain;
import lombok.Getter;

@Getter
public class RecordCreateDto {
    private Member member;
    private Mountain mountain;
    private Course course;
    private Badge badge;
    private Integer level;
    private String content;
}
