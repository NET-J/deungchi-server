package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.RecordLike;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.MemberRepository;
import com.netj.deungchi.repository.RecordLikeRepository;
import com.netj.deungchi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordLikeService {
    public final RecordRepository recordRepository;
    public final MemberRepository memberRepository;
    public final RecordLikeRepository recordLikeRepository;

    public Optional<RecordLike> getMemberRecordLike(Long memberId, Long recordId) {
        return recordLikeRepository.findByMemberIdAndRecordId(memberId, recordId);
    }

    public ResponseDto<?> postRecordLike(Long memberId, Long recordId) {
        Optional<RecordLike> existingRecordLike = getMemberRecordLike(memberId, recordId);

        if (existingRecordLike.isEmpty()) {
            Member member = memberRepository.findById(memberId).orElse(null);
            Record record = recordRepository.findById(recordId).orElse(null);

            if (member != null && record != null) {
                RecordLike recordLike = RecordLike.builder()
                        .member(member)
                        .record(record)
                        .build();

                recordLikeRepository.save(recordLike);
                return ResponseDto.success("");
            } else {
                return ResponseDto.fail(404, "Member or Record not found.", "멤버 또는 기록이 존재하지 않습니다.");
            }
        } else {
            recordLikeRepository.delete(existingRecordLike.get());
            return ResponseDto.success("Record like deleted.");
        }
    }
}
