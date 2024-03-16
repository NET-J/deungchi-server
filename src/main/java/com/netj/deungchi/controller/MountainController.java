package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.MountainService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mountain")
@RequiredArgsConstructor
public class MountainController {

    private final MountainService mountainService;
    private final JwtProvider jwtProvider;

    @GetMapping("/list")
    public ResponseDto<?> getMountainList(HttpServletRequest request) throws Exception {
        Long id = jwtProvider.getIdFromRequest(request);
        return mountainService.getMountainList();
    }

    @GetMapping("/recommendedSearchKeyword")
    public ResponseDto<?> getRecommendedSearchKeyword() {
        return mountainService.getRecommendedSearchKeyword();
    }

    @GetMapping("/search")
    public ResponseDto<?> getMountainsBySearch(@RequestParam String keyword){
        return mountainService.getMountainsBySearch(keyword);
    }

    @GetMapping("/{mountainId}")
    public ResponseDto<?> getMountainDetail(@PathVariable Long mountainId) {
        return mountainService.getMountainDetail(mountainId);
    }
}
