package com.netj.deungchi.service;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.Term;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    public final TermRepository termRepository;

    public ResponseDto<?> getTerm(String termType) {
        return ResponseDto.success(termRepository.findByTermType(termType));
    }

    public ResponseDto<?> getAllTerm() {
        List<Term> terms = termRepository.findAll();
        return ResponseDto.success(terms);
    }
}
