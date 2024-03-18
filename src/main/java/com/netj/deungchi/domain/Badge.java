package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    Mountain mountain;

    @OneToOne
    @JoinColumn(referencedColumnName = "badge_id", name = "id")
    MemberBadge memberBadge;

    String description;
    String version;
    String featuredImage;


    @Builder
    public Badge(Mountain mountain, String name, String description, String version, String featuredImage) {
        this.mountain = mountain;
        this.name = name;
        this.description = description;
        this.version = version;
        this.featuredImage = featuredImage;
    }
}
