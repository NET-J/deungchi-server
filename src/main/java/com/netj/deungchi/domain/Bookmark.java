package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long member_id;

    @Column(insertable=false, updatable=false)
    private Long mountain_id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @OneToOne
    @JoinColumn(referencedColumnName = "id", name = "mountain_id")
    private Mountain mountain;
}
