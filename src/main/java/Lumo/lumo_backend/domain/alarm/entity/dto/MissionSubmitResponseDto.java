package Lumo.lumo_backend.domain.alarm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionSubmitResponseDto {

    private Boolean isCorrect;

    private Boolean isCompleted;

    private Integer remainingQuestions;

    private String message;
}