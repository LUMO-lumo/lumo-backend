package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.AlarmMission;
import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionSettingDto {

    @NotNull(message = "미션 타입은 필수입니다")
    private MissionType missionType;

    private Difficulty difficulty;

    private Integer walkGoalMeter;

    @Builder.Default
    private Integer questionCount = 1;

    public static MissionSettingDto from(AlarmMission mission) {
        if (mission == null) {
            return null;
        }
        return MissionSettingDto.builder()
                .missionType(mission.getMissionType())
                .difficulty(mission.getDifficulty())
                .walkGoalMeter(mission.getWalkGoalMeter())
                .questionCount(mission.getQuestionCount())
                .build();
    }
}