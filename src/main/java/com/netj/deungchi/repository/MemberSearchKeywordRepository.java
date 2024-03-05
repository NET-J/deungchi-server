package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberSearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberSearchKeywordRepository extends JpaRepository<MemberSearchKeyword, Long> {
    List<MemberSearchKeyword> getMemberSearchKeywordsByMember_Id(Long memberId);
}
