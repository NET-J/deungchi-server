package com.netj.deungchi.controller;

import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
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
    public ResponseDto<?> postRecord(@RequestParam Long memberId, @RequestBody RecordPostReqDto recordPostReqDto) {
        return recordService.postRecord(recordPostReqDto, memberId);
    }

}
