package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
