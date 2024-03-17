package com.netj.deungchi.repository;

import com.netj.deungchi.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByTableNameAndTableId(String tableName, Long tableId);

    void deleteAllByTableNameAndTableId(String tableName, Long tableId);
}
