package Lumo.lumo_backend.domain.alarm.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionSubmitDto {

    @NotNull(message = "문제 ID는 필수입니다")
    private Long contentId;

    @NotBlank(message = "답안은 필수입니다")
    private String userAnswer;

    private Integer attemptCount = 1;
}