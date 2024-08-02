package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "select * from record where mountain_id = :mountainId and is_share = 1", nativeQuery = true)
    List<Record> findRecordsByMountainId(@Param("mountainId") Long mountainId);

    @Query(value = "select r.*, m.name as mountain_name, m.featured_image as mountain_featured_image " +
            "from record as r " +
            "inner join mountain m on r.mountain_id = m.id " +
            "WHERE r.member_id = :memberId order by r.id desc", nativeQuery = true)
    List<Record> getMemberRecord(@Param("memberId") Long memberId);

//    @Query(value = "select r.*, m.name as mountain_name, m.featured_image as mountain_featured_image " +
//            "from record as r " +
//            "inner join mountain m on r.mountain_id = m.id " +
//            "WHERE r.member_id = :memberId order by r.id asc", nativeQuery = true)
//    List<Record> getMemberRecordAsc(@Param("memberId") Long memberId);
}
