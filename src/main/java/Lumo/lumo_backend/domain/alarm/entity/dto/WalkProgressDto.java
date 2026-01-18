package Lumo.lumo_backend.domain.alarm.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkProgressDto {

    @NotNull(message = "현재 거리는 필수입니다")
    private Double currentDistance;  // 미터 단위

    public static WalkProgressResponseDto toResponse(Double currentDistance, Integer goalDistance) {
        double progress = (currentDistance / goalDistance) * 100.0;
        boolean isCompleted = currentDistance >= goalDistance;

        return WalkProgressResponseDto.builder()
                .goalDistance(goalDistance)
                .currentDistance(currentDistance)
                .progressPercentage(Math.round(progress * 10.0) / 10.0)
                .isCompleted(isCompleted)
                .build();
    }
}