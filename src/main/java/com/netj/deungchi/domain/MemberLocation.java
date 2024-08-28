package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Member member;

    @JsonIgnore
    @ManyToOne
    private Record record;

    private Double latitude;
    private Double longitude;
    private Double distance;
    private Double totalDistance;

    @CreationTimestamp
    Timestamp createdAt;

    @Builder
    public MemberLocation(Member member, Record record, Double latitude, Double longitude, Double distance, Double totalDistance) {
        this.member = member;
        this.record = record;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.totalDistance = totalDistance;
    }
}
