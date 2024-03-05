package com.netj.deungchi.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateDto {
    private Integer id;
    private String email;
    private String name;
    private String nickname;
    private String phone;
    private Integer isNotiEmail;
    private Integer isNotiSms;
    private Integer isNotiPush;

}
