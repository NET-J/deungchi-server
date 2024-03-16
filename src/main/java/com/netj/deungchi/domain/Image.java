package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tableName;

    @Column(nullable = false)
    private Long tableId;

    @Column(nullable = false)
    private String name;  // 파일 원본명

    private Long size;

    @Column(nullable = false)
    private String url;

    @Builder
    public Image(String name, Long size, String url, String tableName, Long tableId){
        this.name = name;
        this.size = size;
        this.url = url;
        this.tableName = tableName;
        this.tableId = tableId;
    }
}
