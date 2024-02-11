package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RecommendedSearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String searchKeyword;

    @Column
    private Boolean isShow;
}
