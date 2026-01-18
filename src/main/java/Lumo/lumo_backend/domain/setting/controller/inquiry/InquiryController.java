package Lumo.lumo_backend.domain.setting.controller.inquiry;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/inquiries")
public class InquiryController {


    // 사용자 문의사항 조회
    @GetMapping("/{inquiryId}")
    public APIResponse<Object> get() {
        return null;
    }

    // 사용자 문의사항 리스트 조회
    @GetMapping("/list")
    public APIResponse<Object> getList() {
        return null;
    }

    // 유저의 inquiry 생성
    @PostMapping
    public APIResponse<Object> create() {
        return null;
    }

    // 유저의 inquiry 추가 생성
    @PostMapping("/{inquiryId}")
    public APIResponse<Object> create2() {
        return null;
    }

    // 사용자 문의사항 수정
    @PatchMapping("/{inquiryId}")
    public APIResponse<Object> updateInquiry() {
        return null;
    }

}
