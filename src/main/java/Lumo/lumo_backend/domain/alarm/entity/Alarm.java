package Lumo.lumo_backend.domain.alarm.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 알람 엔티티
 * - 사용자가 설정한 알람 정보 저장
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    /**
     * 알람 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long alarmId;

    /**
     * 알람 소유 회원 (FK)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    /**
     * 알람 울릴 시간 (HH:mm)
     */
    @Column(name = "alarm_time", nullable = false)
    private LocalTime alarmTime;

    /**
     * 알람 이름/라벨 (ex: 출근 알람, 약 먹기)
     */
    @Column(name = "label", length = 100)
    private String label;

    /**
     * 알람 활성화 여부 (ON/OFF)
     */
    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled = true;

    /**
     * 알람 사운드 종류
     */
    @Column(name = "sound_type", length = 50)
    private String soundType;

    /**
     * 진동 ON/OFF
     */
    @Column(name = "vibration")
    @Builder.Default
    private Boolean vibration = true;

    /**
     * 알람 음량 (0~100)
     */
    @Column(name = "volume")
    @Builder.Default
    private Integer volume = 50;

    /**
     * 알람 반복 요일 목록 (1:N)
     * - 월,수,금 반복이면 3개 row
     */
    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlarmRepeatDay> repeatDays = new ArrayList<>();

    /**
     * 알람 스누즈 설정 (1:1)
     */
    @OneToOne(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    private AlarmSnooze alarmSnooze;

    /**
     * 알람 미션 설정 (1:1)
     */
    @OneToOne(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    private AlarmMission alarmMission;

    /**
     * 알람 울림 기록 (1:N)
     */
    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlarmLog> alarmLogs = new ArrayList<>();

    /**
     * 미션 수행 기록 (1:N)
     */
    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MissionHistory> missionHistories = new ArrayList<>();

    /**
     * 사운드 타입 설정 (편의 메서드)
     */
    public void setSoundType(AlarmSound alarmSound) {
        this.soundType = alarmSound.name();
    }

    /**
     * 사운드 타입 조회 (편의 메서드)
     */
    public AlarmSound getAlarmSound() {
        return soundType != null ? AlarmSound.valueOf(soundType) : null;
    }
}