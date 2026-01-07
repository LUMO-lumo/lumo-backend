package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.DismissType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmDismissRequestDto {

    @NotNull(message = "알람 ID는 필수입니다")
    private Long alarmId;

    @NotNull(message = "해제 타입은 필수입니다")
    private DismissType dismissType;

    private Integer snoozeCount;
}