package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.DayOfWeek;
import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class AlarmCreateRequestDto {

    @NotNull(message = "알람 시간은 필수입니다")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime alarmTime;

    private String label;

    @Builder.Default
    private Boolean isEnabled = true;

    private String soundType;

    @Builder.Default
    private Boolean vibration = true;

    @Min(0)
    @Max(100)
    @Builder.Default
    private Integer volume = 50;

    private List<DayOfWeek> repeatDays;

    private SnoozeSettingDto snoozeSetting;

    private MissionSettingDto missionSetting;  // 추가!

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SnoozeSettingDto {
        @Builder.Default
        private Boolean isEnabled = true;

        @Builder.Default
        private Integer intervalSec = 300;

        @Builder.Default
        private Integer maxCount = 3;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionSettingDto {
        @NotNull(message = "미션 타입은 필수입니다")
        private MissionType missionType;

        private Difficulty difficulty;

        private Integer walkGoalMeter;

        @Builder.Default
        private Integer questionCount = 1;
    }
}