package com.netj.deungchi.controller;


import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.bookmark.BookmarkCreateDto;
import com.netj.deungchi.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping("/member")
    public ResponseDto<?> getMemberBookmark(@RequestParam Long memberId) {
        return bookmarkService.getMemberBookmark(memberId);
    }

    @PostMapping()
    public ResponseDto<?> postBookmark(@RequestBody BookmarkCreateDto bookmarkCreateDto) {
        return bookmarkService.postBookmark(bookmarkCreateDto);
    }

    @DeleteMapping()
    public ResponseDto<?> deleteBookmark(@RequestParam Long bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);
        return ResponseDto.success(null);
    }
}
