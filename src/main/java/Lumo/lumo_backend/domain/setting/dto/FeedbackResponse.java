package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.Feedback;
import lombok.*;

@Getter
@AllArgsConstructor
public class FeedbackResponse {
    private Long id;
    private String title;
    private String content;

    public static FeedbackResponse from(Feedback feedback) {
        return new FeedbackResponse(
                feedback.getId(),
                feedback.getTitle(),
                feedback.getContent()
        );
    }
}
