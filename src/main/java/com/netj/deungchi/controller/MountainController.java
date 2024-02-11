package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.service.MountainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mountain")
@RequiredArgsConstructor
public class MountainController {

    private final MountainService mountainService;

    @GetMapping("/list")
    public ResponseDto<?> getAllMountains() {
        return mountainService.getAllMountains();
    }

    @GetMapping("/recommendedSearchKeyword")
    public ResponseDto<?> getRecommendedSearchKeyword() {
        return mountainService.getRecommendedSearchKeyword();
    }
}
