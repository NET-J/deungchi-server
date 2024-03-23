package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Mountain mountain;

    @ManyToOne
    private Record record;

    @Column
    private String featuredImage;

    @CreationTimestamp
    Timestamp createdAt;

    @UpdateTimestamp
    Timestamp updatedAt;

    @OneToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "stamp_id", name = "id")
    MemberStamp memberStamp;

    @Builder
    public Stamp(Member member, Mountain mountain, Record record, String featuredImage) {
        this.member = member;
        this.mountain = mountain;
        this.record = record;
        this.featuredImage = featuredImage;
    }
}
