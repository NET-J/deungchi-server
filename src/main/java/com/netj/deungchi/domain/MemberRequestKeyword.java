package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MemberRequestKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Member member;

    @Column(nullable = false)
    private String request_keyword;

    @Builder
    private MemberRequestKeyword(Member member, String request_keyword){
        this.member = member;
        this.request_keyword = request_keyword;
    }

}
