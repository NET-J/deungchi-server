package com.netj.deungchi.controller;

import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.service.RecordService;
import com.netj.deungchi.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;
    private final S3Uploader s3Uploader;
    @GetMapping
    public ResponseDto<?> getRecord(@RequestParam Long id) {
        return recordService.getRecord(id);
    }

    @GetMapping("/all")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PostMapping
    public ResponseDto<?> postRecord(@RequestParam Long memberId, @RequestPart RecordPostReqDto recordPostReqDto, @RequestPart List<MultipartFile> imageList) {
        List<String> imgPaths = s3Uploader.getimgUrlList(imageList);

        return recordService.postRecord(recordPostReqDto, memberId, imgPaths);
    }

}
