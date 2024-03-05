package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Entity
@Getter
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE recommended_search_keyword SET deleted_at = CURRENT_TIMESTAMP where id = ?")
public class RecommendedSearchKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String searchKeyword;

    @Column
    private Boolean isShow;

    @CreationTimestamp
    Timestamp createdAt;
}
