package Lumo.lumo_backend.domain.setting.controller.inquiry;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/admin/inquiries")
public class InquiryController {

    @GetMapping("/{inquiryId}")
    public APIResponse<Object> getInquiry() {
        return null;
    }
    @PostMapping
    public APIResponse<Object> createInquiry() {
        return null;
    }

    @PatchMapping("/{inquiryId}")
    public APIResponse<Object> patchInquiry() {
        return null;
    }

}
