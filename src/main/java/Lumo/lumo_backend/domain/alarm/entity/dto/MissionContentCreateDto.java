package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionContentCreateDto {

    @NotNull(message = "미션 타입은 필수입니다")
    private MissionType missionType;

    @NotNull(message = "난이도는 필수입니다")
    private Difficulty difficulty;

    @NotBlank(message = "문제 내용은 필수입니다")
    private String question;

    @NotBlank(message = "정답은 필수입니다")
    private String answer;
}