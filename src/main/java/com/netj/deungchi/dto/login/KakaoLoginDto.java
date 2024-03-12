package com.netj.deungchi.dto.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLoginDto {
    private String id;
    private String nickname;
    private String email;
    private String profile_image;
}
