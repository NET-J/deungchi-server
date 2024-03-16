package com.netj.deungchi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTerm {
    private Long member_id;
    private Long term_id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;
}
