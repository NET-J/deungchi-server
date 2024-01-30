package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Member member;

    @ManyToOne
    Mountain mountain;

    @ManyToOne
    Course course;

    @ManyToOne
    Badge badge;

    @ManyToOne
    Stamp stamp;

    Integer level;

    String content;

    Boolean is_share;

    Instant start_at;

    Instant end_at;

    Duration hiking_duration;

    @Builder
    public Record(Member member, Mountain mountain, Course course, Badge badge, Integer level, String content, Boolean is_share, Duration hiking_duration) {
        this.member = member;
        this.mountain = mountain;
        this.course = course;
        this.badge = badge;
        this.level = level;
        this.content = content;
        this.is_share = is_share;
        this.hiking_duration = hiking_duration;
    }
}
