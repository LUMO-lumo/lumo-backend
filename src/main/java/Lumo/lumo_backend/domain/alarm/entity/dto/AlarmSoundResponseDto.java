package Lumo.lumo_backend.domain.alarm.entity.dto;

import Lumo.lumo_backend.domain.alarm.entity.AlarmSound;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 알람 사운드 응답 DTO
 * - 클라이언트에서 사운드 선택 UI에 사용
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmSoundResponseDto {

    /**
     * 사운드 식별자 (enum name)
     */
    private String soundId;

    /**
     * 사운드 표시명
     */
    private String displayName;

    /**
     * 기본 사운드 여부
     */
    private Boolean isDefault;

    public static AlarmSoundResponseDto from(AlarmSound alarmSound) {
        return AlarmSoundResponseDto.builder()
                .soundId(alarmSound.name())
                .displayName(alarmSound.getDisplayName())
                .isDefault(alarmSound.isDefaultSound())
                .build();
    }
}