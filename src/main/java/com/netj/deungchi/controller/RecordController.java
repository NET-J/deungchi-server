package com.netj.deungchi.controller;

import com.netj.deungchi.dto.RecordCreateDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/list")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PostMapping
    public ResponseDto<?> postRecord(RecordCreateDto recordCreateDto) {
        return recordService.postRecord(recordCreateDto);
    }
}
