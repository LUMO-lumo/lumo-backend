package Lumo.lumo_backend.domain.setting.controller.notice;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/admin/notices")
public class AdminNoticeController {

    @PostMapping
    public APIResponse<Object> createNotice() {
        return null;
    }

    @PatchMapping("/{noticeId}")
    public APIResponse<Object> patchNotice() {
        return null;
    }


    @DeleteMapping("/{noticeId}")
    public APIResponse<Object> deleteNotice() {
        return null;
    }

}