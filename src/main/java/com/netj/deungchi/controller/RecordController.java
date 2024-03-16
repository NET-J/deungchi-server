package com.netj.deungchi.controller;

import com.netj.deungchi.dto.image.ImagePostDto;
import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.RecordService;
import com.netj.deungchi.service.S3Uploader;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseDto<?> getRecord(@RequestParam Long id) {
        return recordService.getRecord(id);
    }

    @GetMapping("/all")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PostMapping
    public ResponseDto<?> postRecord(HttpServletRequest request, @RequestPart RecordPostReqDto recordPostReqDto, @RequestPart List<MultipartFile> imageList) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);

        List<ImagePostDto> imagePostDtoList = s3Uploader.getimagePostDtoList(imageList);

        return recordService.postRecord(recordPostReqDto, memberId, imagePostDtoList);
    }

}
