package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionContent;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionContentResponseDto {

    private Long contentId;

    private MissionType missionType;

    private Difficulty difficulty;

    private String question;

    private String answer;

    public static MissionContentResponseDto from(MissionContent content) {
        return MissionContentResponseDto.builder()
                .contentId(content.getContentId())
                .missionType(content.getMissionType())
                .difficulty(content.getDifficulty())
                .question(content.getQuestion())
                .answer(content.getAnswer())
                .build();
    }

    public static MissionContentResponseDto fromWithoutAnswer(MissionContent content) {
        return MissionContentResponseDto.builder()
                .contentId(content.getContentId())
                .missionType(content.getMissionType())
                .difficulty(content.getDifficulty())
                .question(content.getQuestion())
                .build();
    }
}