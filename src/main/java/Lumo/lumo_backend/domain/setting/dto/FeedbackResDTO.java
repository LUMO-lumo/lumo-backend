package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.Feedback;
import lombok.*;

@Getter
@AllArgsConstructor
public class FeedbackResDTO {
    private Long id;
    private String title;
    private String content;

    public static FeedbackResDTO from(Feedback feedback) {
        return new FeedbackResDTO(
                feedback.getId(),
                feedback.getTitle(),
                feedback.getContent()
        );
    }
}
