package com.netj.deungchi.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLoginDto {
    private String id;
    private String email;
    private String profileImage;
    private String nickname;
}
