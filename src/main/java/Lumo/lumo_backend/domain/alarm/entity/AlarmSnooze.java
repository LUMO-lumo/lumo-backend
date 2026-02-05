package Lumo.lumo_backend.domain.alarm.entity;

import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 알람 스누즈 설정 엔티티
 * - 알람별 스누즈(다시 알림) 설정 저장 (1:1)
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_snooze")
public class AlarmSnooze extends BaseEntity {

    /**
     * 스누즈 설정 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snooze_id")
    private Long snoozeId;

    /**
     * 소속 알람 (FK, 1:1)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", unique = true, nullable = false)
    private Alarm alarm;

    /**
     * 스누즈 기능 사용 여부
     */
    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled = true;

    /**
     * 스누즈 간격 (초)
     * - 기본값: 300초 (5분)
     * - 피그마 기준: 5초, 10초, 30초, 20초, 1분, 10분
     */
    @Column(name = "interval_sec")
    @Builder.Default
    private Integer intervalSec = 300;

    /**
     * 최대 스누즈 반복 횟수
     * - 기본값: 3회
     */
    @Column(name = "max_count")
    @Builder.Default
    private Integer maxCount = 3;



}