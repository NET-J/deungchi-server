package com.netj.deungchi.controller;

import com.netj.deungchi.dto.image.ImagePostDto;
import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.record.RecordUpdateReqDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.GeoUtils;
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
    private final GeoUtils geoUtils;

    @GetMapping("/{recordId}")
    public ResponseDto<?> getRecordDetail(@PathVariable Long recordId) {
        return recordService.getRecord(recordId);
    }

    @GetMapping("/all")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PostMapping
    public ResponseDto<?> postRecord(HttpServletRequest request, @RequestPart RecordPostReqDto recordPostReqDto, @RequestPart (required = false) List<MultipartFile> imageList) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);

        List<ImagePostDto> imagePostDtoList = s3Uploader.getimagePostDtoList(imageList);

        return recordService.postRecord(recordPostReqDto, memberId, imagePostDtoList);
    }

    @PutMapping("/{recordId}")
    public ResponseDto<?> updateRecordDetail(@PathVariable Long recordId, @RequestPart RecordUpdateReqDto recordUpdateReqDto, @RequestPart (required = false) List<MultipartFile> imageList)  {

        recordService.deleteRecordImage(recordId);
        List<ImagePostDto> imagePostDtoList = s3Uploader.getimagePostDtoList(imageList);

        return recordService.updateRecord(recordId, recordUpdateReqDto, imagePostDtoList);
    }


    @GetMapping("/startLocation/map")
    public ResponseDto<?> getStartLocation(@RequestParam float latitude, @RequestParam float longitutde) {
        return geoUtils.findMountainsInRadius(latitude, longitutde, 500);
    }
}
