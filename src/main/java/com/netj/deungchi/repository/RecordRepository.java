package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    @Query(value = "select * " +
            "from record " +
            "WHERE member_id = ?1 order by desc limit 3", nativeQuery = true)
    List<Record> getMemberRecord(Long memberId);

}
