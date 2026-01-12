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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(nullable = false)
    private boolean batterySaving;

    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultType alarmOffMissionDefaultType;

    @Enumerated(EnumType.STRING)
    @Column
    private AlarmOffMissionDefaultLevel alarmOffMissionDefaultLevel;

    @Column
    private Integer alarmOffMissionDefaultDuration;

    @Column
    private String briefingSentence;

    @Enumerated(EnumType.STRING)
    @Column(length = 500)
    private BriefingVoiceDefaultType briefingVoiceDefaultType;
}
