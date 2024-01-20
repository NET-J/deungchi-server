package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Entity
@Builder
@Getter
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
