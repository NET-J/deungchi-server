package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.MemberTerm;
import com.netj.deungchi.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {

    @Query(value = "select count(1) as cnt from member_term where member_id = ?1 ", nativeQuery = true)
    Long countMemberTerm(Long memberId);
}