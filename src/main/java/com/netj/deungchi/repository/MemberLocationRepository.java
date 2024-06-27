package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long> {
    List<MemberLocation> findAllByMemberIdAndRecordIdOrderByIdAsc(Long memberId, Long recordId);

    Page<MemberLocation> findPageByMemberIdAndRecordIdOrderByIdAsc(Long memberId, Long recordId, Pageable pageable);

    List<MemberLocation> findByMemberIdAndRecordIdAndIdAfter(Long memberId, Long recordId, Long id);
}