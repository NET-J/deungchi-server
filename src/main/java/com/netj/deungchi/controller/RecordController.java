package com.netj.deungchi.controller;

import com.netj.deungchi.dto.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
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
}
