package com.netj.deungchi.dto.member;

import com.netj.deungchi.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class MemberDto {
    private Long id;
    private String nickName;
    private String profileImage;

    @Builder
    public MemberDto(Member member) {
        this.id = member.getId();
        this.nickName = member.getNickname();
        this.profileImage = member.getProfile_image();
    }
}
