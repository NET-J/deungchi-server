package com.netj.deungchi.service;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.bookmark.BookmarkCreateDto;
import com.netj.deungchi.repository.BookmarkRepository;
import com.netj.deungchi.repository.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    public final BookmarkRepository bookmarkRepository;
    private final MountainRepository mountainRepository;

    public ResponseDto<?> getMemberBookmark(Long memberId) {

        List<Bookmark> bookmarks = bookmarkRepository.findByMemberId(memberId);

        return ResponseDto.success(bookmarks);
    }

    public ResponseDto<?> postBookmark(Long memberId, Long mountainId) {
        Mountain mountain = mountainRepository.findById(mountainId).get();

        Bookmark bookmark = Bookmark.builder()
                .member_id(memberId)
                .mountain(mountain)
                .build();

        bookmarkRepository.save(bookmark);
        return ResponseDto.success(bookmark);
    }

    public void deleteBookmark(Long memberId, Long mountainId) {

        bookmarkRepository.deleteMemberBookmark(memberId, mountainId);
    }
}
