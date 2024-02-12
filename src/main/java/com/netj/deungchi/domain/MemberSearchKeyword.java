package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
public class MemberSearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Member member;

    @Column(nullable = false)
    private String search_keyword;

    @CreationTimestamp
    Timestamp createdAt;

    @Builder
    private MemberSearchKeyword (Member member, String search_keyword){
        this.member = member;
        this.search_keyword = search_keyword;
    }

}
