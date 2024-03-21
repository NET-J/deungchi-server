package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberStamp;
import com.netj.deungchi.domain.Stamp;
import com.netj.deungchi.domain.Term;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberStampRepository extends JpaRepository<MemberStamp, Long> {

    @Query(value = "select ms.* " +
            "from member_stamp as ms " +
            "inner join record as r on ms.record_id = r.id " +
            "inner join stamp as s on ms.stamp_id = s.id " +
            "where ms.member_id = ?1 and r.mountain_id = ?2", nativeQuery = true)
    List<MemberStamp> getMemberStampByMountainId(Long memberId, Long mountainId);
}
