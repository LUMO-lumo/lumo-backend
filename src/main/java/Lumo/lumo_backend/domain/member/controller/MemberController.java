package Lumo.lumo_backend.domain.member.controller;

import Lumo.lumo_backend.domain.member.dto.MemberReqDTO;
import Lumo.lumo_backend.domain.member.dto.MemberRespDTO;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.service.MemberService;
import Lumo.lumo_backend.domain.member.status.MemberSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.security.jwt.JWT;
import Lumo.lumo_backend.global.security.userDetails.CustomUserDetails;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public APIResponse<MemberRespDTO.GetLoginDTO> getLoginMethod(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return APIResponse.onSuccess(memberService.getLogin(userDetails.getMember()), MemberSuccessCode.GET_LOGIN_SUCCESS); // 로그인 방식 리턴
    }

    @PostMapping("/login")
    public APIResponse<MemberRespDTO.LoginRespDTO> reqLogin (@RequestBody MemberReqDTO.LoginReqDTO dto, HttpServletResponse response){

        JWT jwt = memberService.login(dto);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", jwt.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 7일
                .sameSite("Strict")
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        MemberRespDTO.LoginRespDTO respDTO = MemberRespDTO.LoginRespDTO.builder().isSuccess(true).accessToken(jwt.getAccessToken()).build();
        return APIResponse.onSuccess(respDTO, MemberSuccessCode.LOGIN_SUCCESS);
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
    public APIResponse<MemberRespDTO.SimpleAPIRespDTO> checkEmailDuplicate(@RequestParam("email") String email) {
        MemberRespDTO.SimpleAPIRespDTO dto = MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(memberService.checkEmailDuplicate(email)).build();
        return APIResponse.onSuccess(dto, MemberSuccessCode.EMAIL_DUPLICATE_CHECK_SUCCESS);
    }

    @PostMapping("/request-code")
    public APIResponse<MemberRespDTO.SimpleAPIRespDTO> requestVerificationCode(@RequestParam("email") String email) {
        memberService.requestVerificationCode(email);
        return APIResponse.onSuccess(MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(true).build(), MemberSuccessCode.REQ_CODE_SUCCESS); // bool 값 리턴,;
    }

    @PostMapping("/verify-code")
    public APIResponse<Object> verifyCode(@RequestParam("code") String code) {
        memberService.verifyCode(code);
        return APIResponse.onSuccess(MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(true).build(), VERIFY_CODE_SUCCESS);
    }

    @PostMapping("/signin")
    public APIResponse<MemberRespDTO.SimpleAPIRespDTO> signIn(@RequestBody MemberReqDTO.SignInRequestDTO dto) {
        memberService.signIn(dto);
        return APIResponse.onSuccess(MemberRespDTO.SimpleAPIRespDTO.builder().isSuccess(true).build(), MemberSuccessCode.SIGN_IN_SUCCESS); // bool 값 리턴,
    }

}
