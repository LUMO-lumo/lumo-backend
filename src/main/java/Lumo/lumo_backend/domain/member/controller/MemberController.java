package Lumo.lumo_backend.domain.member.controller;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class MemberController {


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
    public APIResponse<Object> signIn(@RequestBody ){
        return null; // bool 값 리턴,
    }


}
