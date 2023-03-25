package org.myongjimarket.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.myongjimarket.code.ResponseCode;
import org.myongjimarket.dto.LoginRequestDto;
import org.myongjimarket.dto.SignupRequestDto;
import org.myongjimarket.response.ResResult;
import org.myongjimarket.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> save(@RequestBody SignupRequestDto requestDto) {
        ResponseCode signupSuccess = ResponseCode.SIGNUP_SUCCESS;
        log.info(signupSuccess.getMessage());
        return ResponseEntity.ok(ResResult.builder()
                .responseCode(signupSuccess)
                .code(signupSuccess.getHttpStatus().value())
                .message(signupSuccess.getMessage())
                .data(memberService.save(requestDto))
                .build());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto requestDto) {
        ResponseCode loginSuccess = ResponseCode.LOGIN_SUCCESS;
        log.info(loginSuccess.getMessage());
        return ResponseEntity.ok(ResResult.builder()
                .responseCode(loginSuccess)
                .code(loginSuccess.getHttpStatus().value())
                .message(loginSuccess.getMessage())
                .data(memberService.login(requestDto))
                .build()
        );
    }

    /**
     * 토큰 검증용 멤버 리스트 출력
     */
    @GetMapping
    public ResponseEntity<?> memberList() {
        ResponseCode loginSuccess = ResponseCode.LOGIN_SUCCESS;
        return ResponseEntity.ok(ResResult.builder()
                .responseCode(loginSuccess)
                .code(loginSuccess.getHttpStatus().value())
                .message(loginSuccess.getMessage())
                .data(memberService.memberList())
                .build()
        );
    }
}
