package com.netj.deungchi.service;

import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.Record;
import com.netj.deungchi.domain.RecordGood;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.MemberRepository;
import com.netj.deungchi.repository.RecordGoodRepository;
import com.netj.deungchi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordGoodService {
    public final RecordRepository recordRepository;
    public final MemberRepository memberRepository;
    public final RecordGoodRepository recordGoodRepository;

    public Optional<RecordGood> getMemberRecordLike(Long memberId, Long recordId) {
        return recordGoodRepository.findByMemberIdAndRecordId(memberId, recordId);
    }

    public ResponseDto<?> postRecordLike(Long memberId, Long recordId) {
        Optional<RecordGood> existingRecordGood = getMemberRecordLike(memberId, recordId);

        if (existingRecordGood.isEmpty()) {
            Member member = memberRepository.findById(memberId).orElse(null);
            Record record = recordRepository.findById(recordId).orElse(null);

            if (member != null && record != null) {
                RecordGood recordGood = RecordGood.builder()
                        .member(member)
                        .record(record)
                        .build();

                recordGoodRepository.save(recordGood);
                return ResponseDto.success("");
            } else {
                return ResponseDto.fail(404, "Member or Record not found.", "멤버 또는 기록이 존재하지 않습니다.");
            }
        } else {
            recordGoodRepository.delete(existingRecordGood.get());
            return ResponseDto.success("Record like deleted.");
        }
    }
}
