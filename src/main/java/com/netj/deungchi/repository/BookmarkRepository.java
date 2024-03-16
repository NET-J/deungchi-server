package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Bookmark;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query(value = "select b.mountain_id, m.* " +
            "from bookmark b " +
            "inner join mountain m on b.mountain_id = m.id " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    List<Bookmark> findByMemberIdWithMountain(Long memberId);

    @Query(value = "select b.* " +
            "from bookmark b " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    List<Bookmark> findByMemberId(Long memberId);

    @Transactional
    @Modifying
    @Query(value = "delete from bookmark where member_id = :memberId and mountain_id = :mountainId", nativeQuery = true)
    void deleteMemberBookmark(@Param("memberId") Long memberId, @Param("mountainId") Long mountainId);

    @Query(value = "SELECT * FROM bookmark WHERE member_id = :memberId AND mountain_id = :mountainId", nativeQuery = true)
    Optional<Bookmark> findByMemberIdAndMountainId(@Param("memberId") Long memberId, @Param("mountainId") Long mountainId);

}
