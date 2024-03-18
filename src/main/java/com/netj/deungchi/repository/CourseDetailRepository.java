package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Course;
import com.netj.deungchi.domain.CourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseDetailRepository extends JpaRepository<CourseDetail, Long> {
    List<CourseDetail> findAllByCourseId(Long courseId);
}
