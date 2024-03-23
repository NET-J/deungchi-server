package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    List<MemberBadge> getMemberBadgesByMemberIdAndMountainId(Long memberId, Long mountainId);
}
