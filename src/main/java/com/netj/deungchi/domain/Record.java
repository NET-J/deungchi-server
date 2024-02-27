package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
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

    Timestamp startAt;

    Timestamp endAt;

    String startLocation;

    String end_location;

    // 등산 시간
    String hikingDuration;

    // 등산 거리
    String hikingLength;

    Integer temperature;

    Boolean isShare;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @Builder
    public Record(Member member, Mountain mountain, Course course, Integer level, String content, Timestamp startAt, Timestamp endAt, String startLocation, String end_location, String hikingDuration, String hikingLength, Integer temperature, Boolean isShare) {
        this.member = member;
        this.mountain = mountain;
        this.course = course;
        this.level = level;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.startLocation = startLocation;
        this.end_location = end_location;
        this.hikingDuration = hikingDuration;
        this.hikingLength = hikingLength;
        this.temperature = temperature;
        this.isShare = isShare;
    }
}
