package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String email;
    String name;
    String nickname;
    String phone;
    String profile_image;

    @Builder
    public Member(String name) {
        this.name = name;
    }
}
