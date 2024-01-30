package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.RecordPostDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordService {

    public final RecordRepository recordRepository;

    public ResponseDto<?> getRecord(Long id) {

        Optional<Record> record = recordRepository.findById(id);

        if(record.isEmpty()) {
            log.info("기록이 존재하지 않습니다.");
            return ResponseDto.fail(404, "Record not found", "기록이 존재하지 않습니다.");
        }

        return ResponseDto.success(record.get());
    }

    public ResponseDto<?> getRecordList() {

        List<Record> records = recordRepository.findAll();

        return ResponseDto.success(records);
    }

    public ResponseDto<?> postRecord(RecordPostDto recordPostDto) {

        Record record = Record.builder()
                .member(recordPostDto.getMember())
                .mountain(recordPostDto.getMountain())
                .course(recordPostDto.getCourse())
                .badge(recordPostDto.getBadge())
                .stamp(recordPostDto.getStamp())
                .level(recordPostDto.getLevel())
                .content(recordPostDto.getContent())
                .is_share(recordPostDto.getIsShare())
                .start_at(recordPostDto.getStartAt())
                .end_at(recordPostDto.getEndAt())
                .build();

        Duration hiking_duration = Duration.between(record.getStart_at(), record.getEnd_at());
        record.setHiking_duration(hiking_duration);

        recordRepository.save(record);

        return ResponseDto.success(record);
    }


}
