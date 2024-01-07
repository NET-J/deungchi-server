package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
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

    Integer level;

    String content;

    @Builder
    public Record(Member member, Mountain mountain, Course course, Badge badge, Integer level, String content) {
        this.member = member;
        this.mountain = mountain;
        this.course = course;
        this.badge = badge;
        this.level = level;
        this.content = content;
    }
}
