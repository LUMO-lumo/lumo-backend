package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
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
public class MissionHistoryResponseDto {

    private Long historyId;

    private Long alarmId;

    private MissionType missionType;

    private Boolean isSuccess;

    private Integer attemptCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completedAt;

    public static MissionHistoryResponseDto from(MissionHistory history) {
        return MissionHistoryResponseDto.builder()
                .historyId(history.getHistoryId())
                .alarmId(history.getAlarm().getAlarmId())
                .missionType(history.getMissionType())
                .isSuccess(history.getIsSuccess())
                .attemptCount(history.getAttemptCount())
                .completedAt(history.getCompletedAt())
                .build();
    }
}