package com.netj.deungchi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE record SET deleted_at = CURRENT_TIMESTAMP where id = ?")
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
    String provider;
    String provider_id;
    Timestamp created_at;
    Timestamp updated_at;
    Timestamp deleted_at;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MemberSearchKeyword> recentKeywordList;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Record> recordList;

    @Builder
    public Member(String name) {
        this.name = name;
    }
}
