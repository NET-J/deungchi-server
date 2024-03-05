package com.netj.deungchi.repository;

import com.netj.deungchi.domain.RecommendedSearchKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendedSearchKeywordRepository extends JpaRepository<RecommendedSearchKeyword, Long> {
     List<RecommendedSearchKeyword> findAllByIsShow(Boolean isShow);
}
