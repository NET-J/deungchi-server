package com.netj.deungchi.repository;

import com.netj.deungchi.domain.RecordLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordLikeRepository extends JpaRepository<RecordLike, Long> {
    Optional<RecordLike> findByMemberIdAndRecordId(Long memberId, Long recordId);

    Integer countByRecordId(Long recordId);
}
