package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findAllByMountainIdOrderByIdAsc(Long mountainId);
}
