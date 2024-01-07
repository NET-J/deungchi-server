package com.netj.deungchi.service;

import com.netj.deungchi.domain.Mountain;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.MountainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MountainService {

    public final MountainRepository mountainRepository;
    public ResponseDto<?> getAllMountains() {

        List<Mountain> mountains = mountainRepository.findAll();

        return ResponseDto.success(mountains);
    }
}
