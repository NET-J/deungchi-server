package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "select * from member where provider = ?1 and provider_id = ?2 order by id desc limit 1", nativeQuery = true)
    Member findByProviderId(String provider, String providerId);

}