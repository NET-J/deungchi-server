package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.Duration;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE record SET deleted_at = CURRENT_TIMESTAMP where id = ?")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Member member;

    @JsonIgnore
    @ManyToOne
    Mountain mountain;

    @JsonIgnore
    @ManyToOne
    Course course;

    Integer level;

    String content;

    Boolean is_share;

    Duration hiking_duration;

    Integer temperature;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @Builder
    public Record(Member member, Mountain mountain, Course course, Integer level, String content, Boolean is_share, Duration hiking_duration, Integer temperature) {
        this.member = member;
        this.mountain = mountain;
        this.course = course;
        this.level = level;
        this.content = content;
        this.is_share = is_share;
        this.hiking_duration = hiking_duration;
        this.temperature = temperature;
    }
}
