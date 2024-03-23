package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Badge;
import com.netj.deungchi.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeRepository extends JpaRepository<Badge, Long> {
    @Query(value = "select count(1) " +
            "from badge " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    Long getMemberBadgeCount(Long memberId);

    @Query(value = "select b.*, mnt.name as mountain_name " +
            "from badge as b " +
            "inner join member_badge mb on b.id = mb.badge_id " +
            "inner join mountain mnt on b.mountain_id = mnt.id " +
            "WHERE mb.member_id = ?1 order by b.id desc", nativeQuery = true)
    List<Badge> getMemberBadge(Long memberId);

    @Query(value = "select b.*, mnt.name as mountain_name " +
            "from badge as b " +
            "inner join member_badge mb on b.id = mb.badge_id " +
            "inner join mountain mnt on b.mountain_id = mnt.id " +
            "WHERE mb.member_id = ?1 and b.id = ?2", nativeQuery = true)
    Badge getMemberBadgeBadgeId(Long memberId, Long badgeId);

    @Query(value = "select b.*, mnt.name as mountain_name " +
            "from badge as b " +
            "inner join mountain mnt on b.mountain_id = mnt.id " +
            "WHERE b.id not in ( " +
            "   select badge_id " +
            "   from member_badge " +
            "   where member_id = ?1" +
            ") order by b.id desc", nativeQuery = true)
    List<Badge> getNotMemberBadge(Long memberId);

    @Query(value = "select b.* " +
            "from badge as b " +
            "inner join member_badge mb on b.id = mb.badge_id " +
            "WHERE mb.record_id = ?1 order by b.id desc", nativeQuery = true)
    List<Badge> getMemberBadgeByRecordId(Long recordId);
}
