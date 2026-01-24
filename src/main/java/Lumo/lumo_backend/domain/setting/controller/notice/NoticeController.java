package Lumo.lumo_backend.domain.setting.controller.notice;

import Lumo.lumo_backend.domain.setting.dto.NoticeResponseDTO;
import Lumo.lumo_backend.domain.setting.service.NoticeService;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.apiResponse.status.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/notices")
@Tag(name = "공지사항 조회")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public APIResponse<Object> getNotices() {
        return null;
    }

    @Operation(summary = "공지사항 상세 조회")
    @GetMapping("/{noticeId}")
    public APIResponse<NoticeResponseDTO> getNotice(
            @PathVariable Long noticeId
    ) {
        NoticeResponseDTO noticeResponseDTO = noticeService.get(noticeId);
        return APIResponse.onSuccess(noticeResponseDTO, SuccessCode.OK); //todo 성공 코드 수정
    }
}
