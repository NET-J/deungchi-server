package com.netj.deungchi.repository;

import com.netj.deungchi.domain.MountainLandmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MountainLandmarkRepository extends JpaRepository<MountainLandmark, Long> {
    List<MountainLandmark> findAllByMountainId(Long mountainId);
}
