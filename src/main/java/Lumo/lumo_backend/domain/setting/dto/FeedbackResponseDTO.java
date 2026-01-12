package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.Feedback;
import lombok.*;

@Getter
@AllArgsConstructor
public class FeedbackResponseDTO {
    private Long id;
    private String title;
    private String content;

    public static FeedbackResponseDTO from(Feedback feedback) {
        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getTitle(),
                feedback.getContent()
        );
    }
}
