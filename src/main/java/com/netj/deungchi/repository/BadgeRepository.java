package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    @Query(value = "select count(1) " +
            "from badge " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    Long getMemberBadgeCount(Long memberId);
}
