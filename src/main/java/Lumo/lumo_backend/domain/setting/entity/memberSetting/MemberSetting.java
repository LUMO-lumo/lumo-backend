package Lumo.lumo_backend.domain.setting.entity.memberSetting;


import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;


/**
 * 설정 엔티티
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_setting")
public class MemberSetting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_setting_id")
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme = Theme.SYSTEM;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language = Language.KO;

    @Builder.Default
    @Column(nullable = false)
    private boolean batterySaving = false;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultType alarmOffMissionDefaultType = AlarmOffMissionDefaultType.MATH;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel = AlarmOffMissionDefaultLevel.MEDIUM;

    @Builder.Default
    @Column
    private Integer alarmOffMissionDefaultDuration = 10;

    @Builder.Default
    @Column
    private String briefingSentence = "";

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 500)
    private BriefingVoiceDefaultType briefingVoiceDefaultType = BriefingVoiceDefaultType.WOMAN;

    @Builder.Default
    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean smartBriefing = false;


    public void update(
            Theme theme,
            Language language,
            Boolean batterySaving,
            AlarmOffMissionDefaultType alarmOffMissionDefaultType,
            AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel,
            Integer alarmOffMissionDefaultDuration,
            String briefingSentence,
            BriefingVoiceDefaultType briefingVoiceDefaultType,
            Boolean smartBriefing
    ) {
        if (theme != null) this.theme = theme;
        if (language != null) this.language = language;
        if (batterySaving != null) this.batterySaving = batterySaving;
        if (alarmOffMissionDefaultType != null)
            this.alarmOffMissionDefaultType = alarmOffMissionDefaultType;
        if (alarmOffMissionDefaultLevel != null)
            this.alarmOffMissionDefaultLevel = alarmOffMissionDefaultLevel;
        if (alarmOffMissionDefaultDuration != null)
            this.alarmOffMissionDefaultDuration = alarmOffMissionDefaultDuration;
        if (briefingSentence != null)
            this.briefingSentence = briefingSentence;
        if (briefingVoiceDefaultType != null)
            this.briefingVoiceDefaultType = briefingVoiceDefaultType;
        if (smartBriefing != null)
            this.smartBriefing = smartBriefing;
    }

    public static MemberSetting createDefault() {
        return MemberSetting.builder().build();
    }


}
