package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.domain.setting.dto.FeedbackCreateReqDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackResDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackUpdateReqDTO;
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
            @RequestBody FeedbackCreateReqDTO request
    ) {
        Long memberId = 1L; // test
        return APIResponse.onSuccess(feedbackService.create(memberId, request), SuccessCode.OK);
    }


    @GetMapping("/{feedbackId}")
    public APIResponse<FeedbackResDTO> get(
            @PathVariable Long feedbackId
    ) {

        Long memberId = 1L;
        return APIResponse.onSuccess(feedbackService.get(feedbackId), SuccessCode.OK);
    }

    @PatchMapping("/{feedbackId}")
    public APIResponse<Void> update(
            @PathVariable Long feedbackId,
            @RequestBody FeedbackUpdateReqDTO request
    ) {
        feedbackService.update(feedbackId, request);
        return APIResponse.onSuccess(null, SuccessCode.OK);
    }


}
