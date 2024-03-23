package com.netj.deungchi.dto.record;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecordUpdateReqDto {
    private Long id;
    private String level;
    private String content;
    private Boolean isShare;
    private String deleteImages;
}
