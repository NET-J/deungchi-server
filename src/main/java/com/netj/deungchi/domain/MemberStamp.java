package com.netj.deungchi.domain;

import com.netj.deungchi.service.MemberService;
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
    @JoinColumn(referencedColumnName = "id", name = "member_id")
    Member member;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "record_id")
    Record record;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "stamp_id")
    Stamp stamp;

    @CreationTimestamp
    @Column(name = "created_at")
    Timestamp created_at;
}
