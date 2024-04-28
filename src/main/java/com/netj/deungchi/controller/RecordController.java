package com.netj.deungchi.controller;

import com.netj.deungchi.dto.image.ImagePostDto;
import com.netj.deungchi.dto.record.RecordPostReqDto;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.record.RecordUpdateReqDto;
import com.netj.deungchi.provider.jwt.JwtProvider;
import com.netj.deungchi.service.*;
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
    private final RecordLikeService recordLikeService;
    private final RecordGoodService recordGoodService;
    private final S3Uploader s3Uploader;
    private final JwtProvider jwtProvider;
    private final GeoUtils geoUtils;

    @GetMapping("/{recordId}/endLocation")
    public ResponseDto<?> getEndLocation(@PathVariable Long recordId) {
        return recordService.getEndLocation(recordId);
    }

    @GetMapping("/{recordId}")
    public ResponseDto<?> getRecordDetail(@PathVariable Long recordId) {
        return recordService.getRecord(recordId);
    }

    @GetMapping("/all")
    public ResponseDto<?> getRecordList() {
        return recordService.getRecordList();
    }

    @PutMapping("/{recordId}/end")
    public ResponseDto<?> endRecord(@PathVariable Long recordId, @RequestBody RecordPostReqDto recordPostReqDto) {

        return recordService.endRecord(recordId, recordPostReqDto);
    }

    @PutMapping("/{recordId}/detail")
    public ResponseDto<?> updateRecordDetail(@PathVariable Long recordId, @RequestPart RecordUpdateReqDto recordUpdateReqDto)  {

        return recordService.updateRecordDetail(recordId, recordUpdateReqDto);
    }

    @PostMapping("/{recordId}/images")
    public ResponseDto<?> postRecordImages (@PathVariable Long recordId, @RequestPart (required = false) List<MultipartFile> imageList) {

        List<ImagePostDto> imagePostDtoList = s3Uploader.getimagePostDtoList(imageList);

        return recordService.postRecordImages(recordId, imagePostDtoList);
    }

    @GetMapping("/startLocation/map")
    public ResponseDto<?> getStartLocation(@RequestParam Double latitude, @RequestParam Double longitutde) {
        return geoUtils.findMountainsInRadius(latitude, longitutde, 5000);
    }

    @GetMapping("/startLocation/search")
    public ResponseDto<?> getStartLocation(@RequestParam String keyword) {
        return recordService.getStartLocationBySearch(keyword);
    }

    @PostMapping("/startLocation")
    public ResponseDto<?> postStartLocation(HttpServletRequest request, @RequestParam Long mountainId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return recordService.postStartLocation(memberId, mountainId);
    }

    @PutMapping("/{recordId}/startLocation")
    public ResponseDto<?> updateStartLocation(@PathVariable Long recordId, @RequestParam Long mountainId) {
        return recordService.updateStartLocation(recordId, mountainId);
    }

    @PostMapping("/{recordId}/endLocation")
    public ResponseDto<?> postEndLocation(@PathVariable Long recordId, @RequestParam Long mountainLandmarkId) {
        return recordService.postEndLocation(recordId, mountainLandmarkId);
    }

    @PostMapping("/{recordId}/like")
    public ResponseDto<?> postRecordLike(HttpServletRequest request, @PathVariable Long recordId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return recordLikeService.postRecordLike(memberId, recordId);
    }

    @PostMapping("/{recordId}/good")
    public ResponseDto<?> postRecordGood(HttpServletRequest request, @PathVariable Long recordId) throws Exception {
        Long memberId = jwtProvider.getIdFromRequest(request);
        return recordGoodService.postRecordLike(memberId, recordId);
    }


}
