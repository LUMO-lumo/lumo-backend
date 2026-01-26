package Lumo.lumo_backend.domain.setting.controller.notice;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//@RestController
@Slf4j
@RequestMapping("/api/notices")
public class NoticeController {

    @GetMapping
    public APIResponse<Object> getNotices() {
        return null;
    }

    @GetMapping("/{noticeId}")
    public APIResponse<Object> getNotice() {
        return null;
    }
}
