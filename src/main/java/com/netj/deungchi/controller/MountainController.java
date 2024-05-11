package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.MountainService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mountain")
@RequiredArgsConstructor
public class MountainController {

    private final MountainService mountainService;
    private final JwtProvider jwtProvider;

    @GetMapping("/list")
    public ResponseDto<?> getMountainList(HttpServletRequest request, @RequestParam(required = false) Optional<Boolean>  isMap) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return mountainService.getMountainList(memberId, isMap);
    }

    @GetMapping("/{mountainId}")
    public ResponseDto<?> getMountainDetail(HttpServletRequest request,@PathVariable Long mountainId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return mountainService.getMountainDetail(memberId, mountainId);
    }

    @GetMapping("/{mountainId}/recordList")
    public ResponseDto<?> getRecordList(HttpServletRequest request,@PathVariable Long mountainId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return mountainService.getRecordList(memberId, mountainId);
    }

    @GetMapping("/recommendedSearchKeyword")
    public ResponseDto<?> getRecommendedSearchKeyword() {
        return mountainService.getRecommendedSearchKeyword();
    }

    @GetMapping("/search")
    public ResponseDto<?> getMountainsBySearch(HttpServletRequest request, @RequestParam String keyword) throws Exception{
        Long memberId = jwtProvider.getIdFromRequest(request);
        return mountainService.getMountainsBySearch(memberId, keyword);
    }


}
