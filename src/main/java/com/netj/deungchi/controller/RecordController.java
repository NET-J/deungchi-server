package com.netj.deungchi.controller;

import com.netj.deungchi.dto.record.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.record.RecordEndLocationDto;
import com.netj.deungchi.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;

    @GetMapping
    public ResponseDto<?> getRecord(@RequestParam Long id) {
        return recordService.getRecord(id);
    }

    @GetMapping("/all")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PostMapping
    public ResponseDto<?> postRecord(@RequestBody RecordCreateDto recordCreateDto) {
        return recordService.postRecord(recordCreateDto);
    }

    @PostMapping( value = {"/detail/{recordId}", "/detail"})
    public ResponseDto<?> postRecordEndLocation(@PathVariable(required = false) Long recordId, @RequestBody RecordEndLocationDto recordEndLocationDto) {
        return recordService.postRecordEndLocation(recordId, recordEndLocationDto);
    }
}
