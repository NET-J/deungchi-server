package com.netj.deungchi.controller;

import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.bookmark.BookmarkCreateDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.BookmarkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final JwtProvider jwtProvider;

    @GetMapping("/member")
    public ResponseDto<?> getMemberBookmark(HttpServletRequest request) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return bookmarkService.getMemberBookmark(memberId);
    }

    @PostMapping()
    public ResponseDto<?> postBookmark(@RequestBody BookmarkCreateDto bookmarkCreateDto) {
        return bookmarkService.postBookmark(bookmarkCreateDto);
    }

    @DeleteMapping("/member")
    public ResponseDto<?> deleteBookmark(HttpServletRequest request, @RequestParam Long mountainId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        bookmarkService.deleteBookmark(memberId, mountainId);
        return ResponseDto.success(null);
    }
}
