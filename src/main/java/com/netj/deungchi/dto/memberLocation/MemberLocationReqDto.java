package com.netj.deungchi.dto.memberLocation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberLocationReqDto {
    private Long recordId;
    private Float latitude;
    private Float longitude;
}
