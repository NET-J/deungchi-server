package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    Member member;

    @OneToOne
    Mountain mountain;

    @OneToOne
    Record record;

    @OneToOne
    Stamp stamp;

    @CreationTimestamp
    Timestamp createdAt;

    @Builder
    public MemberStamp(Member member, Mountain mountain,Record record, Stamp stamp) {
        this.member = member;
        this.mountain = mountain;
        this.record = record;
        this.stamp = stamp;
    }
}
