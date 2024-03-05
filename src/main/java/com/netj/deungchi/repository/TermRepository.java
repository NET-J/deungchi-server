package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TermRepository extends JpaRepository<Term, Long> {

    @Query(value = "select * from term where type = ?1 order by id desc limit 1", nativeQuery = true)
    Term findByTermType(String termType);
}
