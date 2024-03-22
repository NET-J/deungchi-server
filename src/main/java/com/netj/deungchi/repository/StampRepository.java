package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findByNameLike(String name);
    List<Stamp> findAllByMountainId(Long mountainId);

    List<Stamp> findAllByMountainIdOrderByIdDesc(Long mountainId);

    List<Stamp> findByIsShow(Boolean isShow);
}
