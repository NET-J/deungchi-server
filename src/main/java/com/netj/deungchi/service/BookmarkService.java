package com.netj.deungchi.service;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.bookmark.BookmarkCreateDto;
import com.netj.deungchi.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    public final BookmarkRepository bookmarkRepository;

    public ResponseDto<?> getMemberBookmark(Long memberId) {

        List<Bookmark> bookmarks = bookmarkRepository.findByMemberId(memberId);

        return ResponseDto.success(bookmarks);
    }

    public ResponseDto<?> postBookmark(BookmarkCreateDto bookmarkCreateDto) {
        Bookmark bookmark = Bookmark.builder()
                .member_id(Long.valueOf(bookmarkCreateDto.getMemberId()))
                .mountain_id(Long.valueOf(bookmarkCreateDto.getMountainId()))
                .build();

        bookmarkRepository.save(bookmark);
        return ResponseDto.success(bookmark);
    }

    public void deleteBookmark(Long memberId, Long mountainId) {

        bookmarkRepository.deleteMemberBookmark(memberId, mountainId);
    }
}
