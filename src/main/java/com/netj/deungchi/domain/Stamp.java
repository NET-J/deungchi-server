package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Mountain mountain;

    @Column(nullable = false)
    private String name;

    @Column
    private String featured_image;

    @OneToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "stamp_id", name = "id")
    MemberStamp memberStamp;
}
