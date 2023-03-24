package org.myongjimarket.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.myongjimarket.domain.Member;
import org.myongjimarket.domain.constant.Campus;
import org.myongjimarket.dto.SignupRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 및 ID 조회 테스트")
    public void saveTest() {
        // given
        SignupRequestDto signupRequestDto = SignupRequestDto.builder()
                .email("email@email.com")
                .password("1234")
                .username("username")
                .campus(Campus.SEOUL)
                .build();

        // when
        Member memberBySaveAndFind = memberService.findMemberById(memberService.save(signupRequestDto));

        // then
        assertThat(memberBySaveAndFind.getEmail()).isEqualTo(signupRequestDto.getEmail());
    }
}