package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.domain.setting.dto.FeedbackCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackResponseDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackUpdateRequestDTO;
import Lumo.lumo_backend.domain.setting.service.FeedbackService;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.apiResponse.status.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 피드백 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public APIResponse<Long> create(
            @RequestBody FeedbackCreateRequestDTO request
    ) {
        Long memberId = 1L; // test
        return APIResponse.onSuccess(feedbackService.create(memberId, request), SuccessCode.OK);
    }


    @GetMapping("/{feedbackId}")
    public APIResponse<FeedbackResponseDTO> get(
            @PathVariable Long feedbackId
    ) {

        Long memberId = 1L;
        return APIResponse.onSuccess(feedbackService.get(feedbackId), SuccessCode.OK);
    }

    @PatchMapping("/{feedbackId}")
    public APIResponse<Void> update(
            @PathVariable Long feedbackId,
            @RequestBody FeedbackUpdateRequestDTO request
    ) {
        feedbackService.update(feedbackId, request);
        return APIResponse.onSuccess(null, SuccessCode.OK);
    }


}
