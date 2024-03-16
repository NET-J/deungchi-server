package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.TermService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/term")
public class TermController {
    private final TermService termService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseDto<?> getTerm(@RequestParam String termType) {
        return termService.getTerm(termType);
    }

    @GetMapping("/all")
    public ResponseDto<?> getAllTerm() {
        return termService.getAllTerm();
    }

    @PostMapping("/member")
    public ResponseDto<?> postMemberTerm(@RequestParam String marketing, HttpServletRequest request) throws Exception {
        Long id = jwtProvider.getIdFromRequest(request);
        return termService.postMemberTerm(id, marketing);
    }
}
