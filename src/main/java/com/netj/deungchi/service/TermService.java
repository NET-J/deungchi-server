package com.netj.deungchi.service;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.MemberTerm;
import com.netj.deungchi.domain.Term;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.MemberTermRepository;
import com.netj.deungchi.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {
    public final TermRepository termRepository;
    public final MemberTermRepository memberTermRepository;

    public ResponseDto<?> getTerm(String termType) {
        return ResponseDto.success(termRepository.findByTermType(termType));
    }

    public ResponseDto<?> getAllTerm() {
        List<Term> terms = termRepository.findAll();
        return ResponseDto.success(terms);
    }

    public ResponseDto<?> postMemberTerm(Long memberId, Boolean isMarketing) {
        int length = isMarketing ? 5 : 4;
        for (int i = 1; i <= length; i++) {
            MemberTerm memberTerm = MemberTerm.builder()
                    .member_id(memberId)
                    .term_id((long) i)
                    .build();

            memberTermRepository.save(memberTerm);
        }

        return ResponseDto.success(true);
    }
}
