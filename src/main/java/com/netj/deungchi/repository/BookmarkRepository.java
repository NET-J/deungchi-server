package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookmarkRepository  extends JpaRepository<Bookmark, Long> {
    @Query(value = "select b.mountain_id, m.* " +
            "from bookmark b " +
            "inner join mountain m on b.mountain_id = m.id " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    List<Bookmark> findByMemberIdWithMountain(Long memberId);

    @Query(value = "select b.* " +
            "from bookmark b " +
            "WHERE b.member_id = ?1", nativeQuery = true)
    List<Bookmark> findByMemberId(Long memberId);
}
