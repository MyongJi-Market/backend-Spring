package org.myongjimarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myongjimarket.code.ResponseCode;
import org.myongjimarket.dto.SignupRequestDto;
import org.myongjimarket.response.ResResult;
import org.myongjimarket.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> save(@RequestBody SignupRequestDto requestDto) {
        log.info(ResponseCode.SIGNUP_SUCCESS.getMessage());
        ResponseCode signupSuccess = ResponseCode.SIGNUP_SUCCESS;
        return ResponseEntity.ok(ResResult.builder()
                .responseCode(signupSuccess)
                .code(signupSuccess.getHttpStatus().value())
                .message(signupSuccess.getMessage())
                .data(memberService.save(requestDto))
                .build());
    }
}
