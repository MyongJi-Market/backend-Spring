package org.myongjimarket.service;

import lombok.RequiredArgsConstructor;
import org.myongjimarket.domain.Member;
import org.myongjimarket.dto.MemberResponseDto;
import org.myongjimarket.dto.SignupRequestDto;
import org.myongjimarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(SignupRequestDto requestDto) {
        Member member = Member.toEntity(requestDto);
        return memberRepository.save(member).getId();
    }
}
