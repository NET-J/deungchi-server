package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.record.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public ResponseDto<?> postRecord(RecordCreateDto recordCreateDto) {

        Record record = Record.builder()
                .member(recordCreateDto.getMember())
                .mountain(recordCreateDto.getMountain())
                .course(recordCreateDto.getCourse())
                .level(recordCreateDto.getLevel())
                .content(recordCreateDto.getContent())
                .is_share(recordCreateDto.getIsShare())
                .hiking_duration(recordCreateDto.getHiking_duration())
                .build();

        recordRepository.save(record);

        return ResponseDto.success(record);
    }


}
