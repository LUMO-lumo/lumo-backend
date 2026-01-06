package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.AlarmLog;
import Lumo.lumo_backend.domain.alarm.entity.DismissType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmLogResponseDto {

    private Long logId;

    private Long alarmId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime triggeredAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dismissedAt;

    private DismissType dismissType;

    private Integer snoozeCount;

    public static AlarmLogResponseDto from(AlarmLog log) {
        return AlarmLogResponseDto.builder()
                .logId(log.getLogId())
                .alarmId(log.getAlarm().getAlarmId())
                .triggeredAt(log.getTriggeredAt())
                .dismissedAt(log.getDismissedAt())
                .dismissType(log.getDismissType())
                .snoozeCount(log.getSnoozeCount())
                .build();
    }
}