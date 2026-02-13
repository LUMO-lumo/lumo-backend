package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AlarmResponseDto {

    private Long alarmId;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime alarmTime;

    private String label;

    private Boolean isEnabled;

    private String soundType;

    private Boolean vibration;

    private Integer volume;

    private List<DayOfWeek> repeatDays;

    private SnoozeSettingResponseDto snoozeSetting;

    private MissionSettingDto missionSetting;  // ← 추가


    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SnoozeSettingResponseDto {
        private Long snoozeId;
        private Boolean isEnabled;
        private Integer intervalSec;
        private Integer maxCount;
    }


    public static AlarmResponseDto from(Alarm alarm) {
        return AlarmResponseDto.builder()
                .alarmId(alarm.getAlarmId())
                .alarmTime(alarm.getAlarmTime())
                .label(alarm.getLabel())
                .isEnabled(alarm.getIsEnabled())
                .soundType(alarm.getSoundType())
                .vibration(alarm.getVibration())
                .volume(alarm.getVolume())
                .repeatDays(alarm.getRepeatDays().stream()
                        .map(AlarmRepeatDay::getDayOfWeek)
                        .toList())
                .snoozeSetting(alarm.getAlarmSnooze() != null ?
                        SnoozeSettingResponseDto.builder()
                                .snoozeId(alarm.getAlarmSnooze().getSnoozeId())
                                .isEnabled(alarm.getAlarmSnooze().getIsEnabled())
                                .intervalSec(alarm.getAlarmSnooze().getIntervalSec())
                                .maxCount(alarm.getAlarmSnooze().getMaxCount())
                                .build() : null)
                .missionSetting(MissionSettingDto.from(alarm.getAlarmMission()))  // ← 추가
                .build();
    }
}