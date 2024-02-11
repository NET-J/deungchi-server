package com.netj.deungchi.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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
    Integer is_noti_email;
    Integer is_noti_sms;
    Integer is_noti_push;
    Timestamp created_at;
    Timestamp updated_at;
    Timestamp deleted_at;

    @OneToMany(mappedBy = "member")
    private List<MemberSearchKeyword> recentKeyword;

    @Builder
    public Member(String name) {
        this.name = name;
    }
}
