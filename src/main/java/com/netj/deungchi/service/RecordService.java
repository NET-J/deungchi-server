package com.netj.deungchi.service;

import com.netj.deungchi.domain.Record;
import com.netj.deungchi.dto.record.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.record.RecordEndLocationDto;
import com.netj.deungchi.repository.RecordRepository;
import jakarta.persistence.EntityManager;
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
    private final EntityManager entityManager;

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
                .isShare(recordCreateDto.getIsShare())
                .hikingDuration(recordCreateDto.getHikingDuration())
                .build();

        recordRepository.save(record);

        return ResponseDto.success(record);
    }

    public ResponseDto<?> postRecordEndLocation(Long recordId, RecordEndLocationDto recordEndLocationDto) {
        if(recordId != null) {
            Record record = entityManager.find(Record.class, recordId);
            record.setEnd_location(recordEndLocationDto.getEndLocation());

            return ResponseDto.success(record);
        } else {
            Record record = Record.builder()
                    .member(recordEndLocationDto.getMember())
                    .mountain(recordEndLocationDto.getMountain())
                    .course(recordEndLocationDto.getCourse())
                    .end_location(recordEndLocationDto.getEndLocation())
                    .build();
            recordRepository.save(record);

            return ResponseDto.success(record);
        }
    }


}
