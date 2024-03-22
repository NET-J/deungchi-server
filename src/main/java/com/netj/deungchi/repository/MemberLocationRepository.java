package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long> {
    List<MemberLocation> findAllByMemberIdAndRecordIdOrderByIdAsc(Long memberId, Long recordId);
}