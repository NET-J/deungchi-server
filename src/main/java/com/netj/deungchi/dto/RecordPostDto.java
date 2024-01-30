package com.netj.deungchi.dto;

import com.netj.deungchi.domain.*;
import lombok.Getter;

import java.time.Instant;

@Getter
public class RecordPostDto {
    private Member member;
    private Mountain mountain;
    private Course course;
    private Badge badge;
    private Stamp stamp;
    private Integer level;
    private String content;
    private Boolean isShare;
    private Instant startAt;
    private Instant endAt;
}
