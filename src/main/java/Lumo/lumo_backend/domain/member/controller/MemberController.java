package Lumo.lumo_backend.domain.member.controller;

import Lumo.lumo_backend.domain.member.dto.MemberRequestDTO;
import Lumo.lumo_backend.domain.member.service.MemberService;
import Lumo.lumo_backend.domain.member.status.MemberSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    ///  자동 로그인, 이메일 기억하기 기능도 추가가 필요!

    @GetMapping("/login")
    public APIResponse<Object> getLoginMethod (){
        return null; // 로그인 방식 리턴
    }

    @PostMapping("/logout")
    public APIResponse<Object> logout (){
        return null; // bool 값 리턴
    }

    @PostMapping("/withdrawl")
    public APIResponse<Object> withdrawal(){
        return null; // bool 값 리턴, SOFT DELETE
    }

    @PostMapping("/signin")
    public APIResponse<Object> signIn(@RequestBody MemberRequestDTO.SignInRequestDTO dto){
        return null; // bool 값 리턴,
    }

    @GetMapping("/email-duplicate")
    public APIResponse<Object> checkEmailDuplicate (@RequestParam("email") String email){
        return APIResponse.onSuccess(memberService.checkEmailDuplicate(email), MemberSuccessCode.EMAIL_DUPLICATE_CHECK_SUCCESS);
    }

    @PostMapping("/verfication-code")
        public APIResponse<Object> requestVerificationCode(@RequestParam("email") String email){
        memberService.requestVerificationCode(email);
        return null;
    }


}
