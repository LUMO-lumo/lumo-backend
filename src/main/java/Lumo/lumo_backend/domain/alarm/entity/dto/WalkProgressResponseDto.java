package Lumo.lumo_backend.domain.alarm.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalkProgressResponseDto {
    private Integer goalDistance;
    private Double currentDistance;
    private Double progressPercentage;
    private Boolean isCompleted;
}