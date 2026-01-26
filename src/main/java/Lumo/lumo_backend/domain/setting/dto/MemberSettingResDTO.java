package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.memberSetting.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSettingResDTO {
    private Theme theme;
    private Language language;
    private boolean batterySaving;
    private AlarmOffMissionDefaultType alarmOffMissionDefaultType;
    private AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel;
    private Integer alarmOffMissionDefaultDuration;
    private String briefingSentence;
    private BriefingVoiceDefaultType briefingVoiceDefaultType;

    public static MemberSettingResDTO from(MemberSetting memberSetting) {
        return new MemberSettingResDTO(
                memberSetting.getTheme(),
                memberSetting.getLanguage(),
                memberSetting.isBatterySaving(),
                memberSetting.getAlarmOffMissionDefaultType(),
                memberSetting.getAlarmOffMissionDefaultLevel(),
                memberSetting.getAlarmOffMissionDefaultDuration(),
                memberSetting.getBriefingSentence(),
                memberSetting.getBriefingVoiceDefaultType()
        );
    }
}
