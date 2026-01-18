package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.DayOfWeek;
import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmUpdateRequestDto {

    @JsonFormat(pattern = "HH:mm")
    private LocalTime alarmTime;

    private String label;

    private String soundType;

    private Boolean vibration;

    @Min(0)
    @Max(100)
    private Integer volume;

    private List<DayOfWeek> repeatDays;

    private AlarmCreateRequestDto.SnoozeSettingDto snoozeSetting;

}