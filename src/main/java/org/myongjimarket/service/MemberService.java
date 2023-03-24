package org.myongjimarket.service;

import lombok.RequiredArgsConstructor;
import org.myongjimarket.code.ErrorCode;
import org.myongjimarket.domain.Member;
import org.myongjimarket.dto.MemberResponseDto;
import org.myongjimarket.dto.SignupRequestDto;
import org.myongjimarket.exception.EmailAlreadyExistException;
import org.myongjimarket.exception.MemberNotFoundException;
import org.myongjimarket.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long save(SignupRequestDto requestDto) {
        duplicateSignupCheck(requestDto.getEmail());
        return memberRepository.save(Member.toEntity(requestDto)).getId();
    }

    public MemberResponseDto findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(Member::toResponseDto)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }


    /**
     * 중복 회원가입 체크.
     * @param email -> 유니크 필드인 이메일 값을 통해 조회.
     */
    private void duplicateSignupCheck(String email) {
        if (memberRepository.existsByEmail(email)) throw new EmailAlreadyExistException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
