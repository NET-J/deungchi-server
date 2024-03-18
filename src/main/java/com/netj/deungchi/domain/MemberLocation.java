package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
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

    private Float latitude;
    private Float longitude;

    @CreationTimestamp
    Timestamp createdAt;

    @Builder
    public MemberLocation(Member member, Record record, Float latitude, Float longitude) {
        this.member = member;
        this.record = record;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
