package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberStatisticsResponseDto {

    /**
     * 이번 달 미션 달성률 (%)
     */
    private Integer missionSuccessRate;

    /**
     * 미션 연속 성공 일수
     */
    private Integer consecutiveSuccessDays;

    /**
     * 이번 달 총 미션 시도 횟수
     */
    private Integer totalMissionAttempts;

    /**
     * 이번 달 미션 성공 횟수
     */
    private Integer totalMissionSuccess;

    /**
     * 이번 달 알람 울림 횟수
     */
    private Integer totalAlarmTriggered;

    public static MemberStatisticsResponseDto from(
            Member member,
            int totalAttempts,
            int totalSuccess,
            int totalTriggered
    ) {
        return MemberStatisticsResponseDto.builder()
                .missionSuccessRate(member.getMissionSuccessRate())
                .consecutiveSuccessDays(member.getConsecutiveSuccessCnt())
                .totalMissionAttempts(totalAttempts)
                .totalMissionSuccess(totalSuccess)
                .totalAlarmTriggered(totalTriggered)
                .build();
    }
}
