package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/term")
public class TermController {
    private final TermService termService;

    @GetMapping
    public ResponseDto<?> getTerm(@RequestParam String termType) {
        return termService.getTerm(termType);
    }

    @GetMapping("/all")
    public ResponseDto<?> getAllTerm() {
        return termService.getAllTerm();
    }
}
