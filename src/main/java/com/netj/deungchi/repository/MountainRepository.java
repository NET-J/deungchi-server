package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MountainRepository extends JpaRepository<Mountain, Long> {
    List<Mountain> findByNameLike(String name);

    List<Mountain> findByNameOrLocationLike(String nameKeyword, String locationKeyword);

    List<Mountain> findByIsShow(Boolean isShow);
}
