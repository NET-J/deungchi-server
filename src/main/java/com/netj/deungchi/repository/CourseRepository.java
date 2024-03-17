package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByMountainId(Long mountainId);
}
