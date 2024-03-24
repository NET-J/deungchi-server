package com.netj.deungchi.repository;

import com.netj.deungchi.domain.RecordGood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordGoodRepository extends JpaRepository<RecordGood, Long> {
    Optional<RecordGood> findByMemberIdAndRecordId(Long memberId, Long recordId);

    Integer countByRecordId(Long recordId);
}
