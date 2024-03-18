package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MemberLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLocationRepository extends JpaRepository<MemberLocation, Long> {
}