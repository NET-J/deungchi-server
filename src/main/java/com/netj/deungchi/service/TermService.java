package com.netj.deungchi.service;

import com.netj.deungchi.domain.Bookmark;
import com.netj.deungchi.domain.Member;
import com.netj.deungchi.domain.MemberTerm;
import com.netj.deungchi.domain.Term;
import com.netj.deungchi.dto.ResponseDto;
import com.netj.deungchi.repository.MemberRepository;
import com.netj.deungchi.repository.MemberTermRepository;
import com.netj.deungchi.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TermService {
    public final TermRepository termRepository;
    public final MemberTermRepository memberTermRepository;
    public final MemberRepository memberRepository;

    public ResponseDto<?> getTerm(String termType) {
        return ResponseDto.success(termRepository.findByTermType(termType));
    }

    public ResponseDto<?> getAllTerm() {
        List<Term> terms = termRepository.findAll();
        return ResponseDto.success(terms);
    }

    public ResponseDto<?> postMemberTerm(Long memberId, String marketing) {
        int length = Objects.equals(marketing, "agree") ? 5 : 4;
        for (int i = 1; i <= length; i++) {
            MemberTerm memberTerm = MemberTerm.builder()
                    .member_id(memberId)
                    .term_id((long) i)
                    .build();

            memberTermRepository.save(memberTerm);
        }

        if (Objects.equals(marketing, "agree")) {
            Member member = memberRepository.findById(memberId).get();

            member.setIs_noti_push(1);
            member.setIs_noti_email(1);
            member.setIs_noti_email(1);
        }

        return ResponseDto.success(true);
    }
}
