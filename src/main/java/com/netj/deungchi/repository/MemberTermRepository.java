package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.MemberTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermRepository extends JpaRepository<MemberTerm, Long> {
}