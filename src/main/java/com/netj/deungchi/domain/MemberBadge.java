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
public class MemberBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "id", name = "member_id")
    Member member;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "mountain_id")
    Mountain mountain;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "record_id")
    Record record;

    @OneToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "id", name = "badge_id")
    Badge badge;

    @CreationTimestamp
    Timestamp createdAt;

    @Builder
    public MemberBadge(Member member, Mountain mountain,Record record, Badge badge) {
        this.member = member;
        this.mountain = mountain;
        this.record = record;
        this.badge = badge;
    }
}
