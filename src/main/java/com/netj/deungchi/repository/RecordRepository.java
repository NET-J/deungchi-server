package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findRecordsByMountainId(Long mountainId);

    @Query(value = "select r.*, m.name as mountain_name, m.featured_image as mountain_featured_image " +
            "from record as r " +
            "inner join mountain m on r.mountain_id = m.id " +
            "WHERE r.member_id = ?1", nativeQuery = true)
    List<Record> getMemberRecord(Long memberId);
}
