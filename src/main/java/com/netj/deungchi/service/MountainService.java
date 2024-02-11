package com.netj.deungchi.service;

import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.dto.mountain.MountainListDto;
import com.netj.deungchi.repository.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MountainService {

    public final MountainRepository mountainRepository;
    public ResponseDto<?> getAllMountains() {

        List<Mountain> mountains = mountainRepository.findAll();
        List<MountainListDto> result = mountains.stream().limit(10).map(MountainListDto::new).collect(Collectors.toList());

        return ResponseDto.success(result);
    }
}
