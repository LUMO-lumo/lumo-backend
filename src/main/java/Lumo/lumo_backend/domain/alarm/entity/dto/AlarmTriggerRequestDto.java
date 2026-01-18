package Lumo.lumo_backend.domain.alarm.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmTriggerRequestDto {

    @NotNull(message = "알람 ID는 필수입니다")
    private Long alarmId;
}