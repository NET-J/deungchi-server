package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findRecordsByMountainId(Long mountainId);
}
