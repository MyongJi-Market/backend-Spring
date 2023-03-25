package org.myongjimarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myongjimarket.code.ErrorCode;
import org.myongjimarket.config.jwt.JwtProvider;
import org.myongjimarket.domain.Member;
import org.myongjimarket.domain.RefreshToken;
import org.myongjimarket.dto.LoginRequestDto;
import org.myongjimarket.dto.MemberResponseDto;
import org.myongjimarket.dto.SignupRequestDto;
import org.myongjimarket.dto.TokenInfo;
import org.myongjimarket.exception.EmailAlreadyExistException;
import org.myongjimarket.exception.LoginFailureException;
import org.myongjimarket.exception.MemberNotFoundException;
import org.myongjimarket.repository.MemberRepository;
import org.myongjimarket.repository.RefreshTokenRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public Long save(SignupRequestDto requestDto) {
        duplicateSignupCheck(requestDto.getEmail());
        return memberRepository.save(Member.toEntity(requestDto, passwordEncoder)).getId();
    }

    @Transactional(readOnly = true)
    public TokenInfo login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND)
        );

        // 비밀번호 검증
        validatePassword(requestDto.getPassword(), member.getPassword());

        // 이메일과 권한으로 Authentication 반환.
        Authentication authentication = jwtProvider.getAuthentication(requestDto.getEmail(), member.getAuthority());
        TokenInfo tokenInfo = jwtProvider.generateToken(authentication);

        // 리프레쉬 토큰 저장.
        RefreshToken refreshToken = jwtProvider.generateRefreshToken(authentication, tokenInfo);
        refreshTokenRepository.save(refreshToken);

        return tokenInfo;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(Member::toResponseDto)
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional(readOnly = true)
    public List<Member> memberList() {
        return memberRepository.findAll();
    }


    /**
     * 중복 회원가입 체크.
     * @param email -> 유니크 필드인 이메일 값을 통해 조회.
     */

    private void duplicateSignupCheck(String email) {
        if (memberRepository.existsByEmail(email)) throw new EmailAlreadyExistException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }


    private void validatePassword(String reqPassword, String password) {
        if (!passwordEncoder.matches(reqPassword, password))
            throw new LoginFailureException(ErrorCode.LOGIN_FAIL);
    }
}
