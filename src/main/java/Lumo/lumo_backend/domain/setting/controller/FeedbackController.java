package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 피드백 컨트롤러
 */
@RestController
@Slf4j
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @GetMapping
    public APIResponse<Object> getFeedbacks() {
        return null;
    }

    @PostMapping
    public APIResponse<Object> createFeedback() {
        return null;
    }

    @PatchMapping("/{feedbackId}")
    public APIResponse<Object> updateFeedback() {
        return null;
    }


}
