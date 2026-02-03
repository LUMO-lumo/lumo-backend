package Lumo.lumo_backend.domain.setting.entity.memberSetting;


import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

//    @OneToOne
//    @JoinColumn(name = "member_id", nullable = false, unique = true)
//    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme = Theme.SYSTEM;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language = Language.KO;

    @Column(nullable = false)
    private boolean batterySaving = false;

    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultType alarmOffMissionDefaultType = AlarmOffMissionDefaultType.MATH;

    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel = AlarmOffMissionDefaultLevel.MEDIUM;

    @Column
    private Integer alarmOffMissionDefaultDuration = 10;

    @Column
    private String briefingSentence = "";

    @Enumerated(EnumType.STRING)
    @Column(length = 500)
    private BriefingVoiceDefaultType briefingVoiceDefaultType = BriefingVoiceDefaultType.WOMAN;


    public void update(
            Theme theme,
            Language language,
            boolean batterySaving,
            AlarmOffMissionDefaultType alarmOffMissionDefaultType,
            AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel,
            Integer alarmOffMissionDefaultDuration,
            String briefingSentence,
            BriefingVoiceDefaultType briefingVoiceDefaultType

    ) {
        this.theme = theme;
        this.language = language;
        this.batterySaving = batterySaving;
        this.alarmOffMissionDefaultType = alarmOffMissionDefaultType;
        this.alarmOffMissionDefaultLevel = alarmOffMissionDefaultLevel;
        this.alarmOffMissionDefaultDuration = alarmOffMissionDefaultDuration;
        this.briefingSentence = briefingSentence;
        this.briefingVoiceDefaultType = briefingVoiceDefaultType;
    }

    public static MemberSetting createDefault() {
        return MemberSetting.builder()
//                .member(member)
                .theme(Theme.LIGHT)
                .language(Language.KO)
                .build();
    }
}
