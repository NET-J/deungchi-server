package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    public final RecordRepository recordRepository;

    public ResponseDto<?> getRecordList() {

        List<Record> records = recordRepository.findAll();

        return ResponseDto.success(records);
    }

    public ResponseDto<?> postRecord(RecordCreateDto recordCreateDto) {

        Record record = Record.builder()
                .member(recordCreateDto.getMember())
                .mountain(recordCreateDto.getMountain())
                .course(recordCreateDto.getCourse())
                .badge(recordCreateDto.getBadge())
                .level(recordCreateDto.getLevel())
                .content(recordCreateDto.getContent())
                .build();

        recordRepository.save(record);

        return ResponseDto.success(record);
    }
}
