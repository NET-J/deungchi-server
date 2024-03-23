package com.netj.deungchi.domain;

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

    @ManyToOne
    Mountain mountain;

    @ManyToOne
    Course course;

    @ManyToOne
    CourseDetail courseDetail;

    String level;

    String content;

    Timestamp startAt;

    Timestamp endAt;

    String startLocation;

    // 목적지
    String endLocation;

    // 등산 시간
    String hikingDuration;

    // 등산 거리
    Double hikingLength;

    Integer temperature;
    Integer weatherCode;

    Boolean isShare;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @Builder
    public Record(Member member, Mountain mountain, Course course, CourseDetail courseDetail,String level, String content, Timestamp startAt, Timestamp endAt, String startLocation, String endLocation, String hikingDuration, Double hikingLength, Integer temperature, Integer weatherCode, Boolean isShare) {
        this.member = member;
        this.mountain = mountain;
        this.course = course;
        this.courseDetail = courseDetail;
        this.level = level;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.hikingDuration = hikingDuration;
        this.hikingLength = hikingLength;
        this.temperature = temperature;
        this.weatherCode = weatherCode;
        this.isShare = isShare;
    }
}
