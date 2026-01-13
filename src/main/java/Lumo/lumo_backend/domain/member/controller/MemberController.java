package Lumo.lumo_backend.domain.member.controller;

import Lumo.lumo_backend.domain.member.dto.MemberRequestDTO;
import Lumo.lumo_backend.domain.member.dto.MemberRespDTO;
import Lumo.lumo_backend.domain.member.service.MemberService;
import Lumo.lumo_backend.domain.member.status.MemberSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static Lumo.lumo_backend.domain.member.status.MemberSuccessCode.VERIFY_CODE_SUCCESS;

@RestController
@Slf4j
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    ///  자동 로그인, 이메일 기억하기 기능도 추가가 필요!

    @GetMapping("/login")
    public APIResponse<Object> getLoginMethod() {
        return null; // 로그인 방식 리턴
    }

    @PostMapping("/logout")
    public APIResponse<Object> logout() {
        return null; // bool 값 리턴
    }

    @PostMapping("/withdrawl")
    public APIResponse<Object> withdrawal() {
        return null; // bool 값 리턴, SOFT DELETE
    }

    @GetMapping("/email-duplicate")
    public APIResponse<Object> checkEmailDuplicate(@RequestParam("email") String email) {
        return APIResponse.onSuccess(memberService.checkEmailDuplicate(email), MemberSuccessCode.EMAIL_DUPLICATE_CHECK_SUCCESS);
    }

    @PostMapping("/request-code")
    public APIResponse<Object> requestVerificationCode(@RequestParam("email") String email) {
        memberService.requestVerificationCode(email);
        return null;
    }

    @PostMapping("/verify-code")
    public APIResponse<Object> verifyCode(@RequestParam("code") String code) {
        memberService.verifyCode(code);
        return APIResponse.onSuccess(MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(true).build(), VERIFY_CODE_SUCCESS);
    }

    @PostMapping("/signin")
    public APIResponse<MemberRespDTO.SimpleAPIRespDTO> signIn(@RequestBody MemberRequestDTO.SignInRequestDTO dto) {
        memberService.signIn(dto);
        return APIResponse.onSuccess(MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(true).build(), MemberSuccessCode.SIGN_IN_SUCCESS); // bool 값 리턴,
    }

}
