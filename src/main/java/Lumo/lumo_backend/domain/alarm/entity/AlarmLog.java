package Lumo.lumo_backend.domain.alarm.entity;

import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 알람 울림 기록 엔티티
 * - 알람이 울린 시간, 해제 방식 등 기록
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_log")
public class AlarmLog extends BaseEntity {

    /**
     * 로그 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    /**
     * 울린 알람 (FK)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", nullable = false)
    private Alarm alarm;

    /**
     * 알람 실제 울린 시간
     */
    @Column(name = "triggered_at", nullable = false)
    private LocalDateTime triggeredAt;

    /**
     * 알람 해제한 시간
     */
    @Column(name = "dismissed_at")
    private LocalDateTime dismissedAt;

    /**
     * 알람 해제 방식 (MISSION/SNOOZE/MANUAL)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "dismiss_type", columnDefinition = "ENUM('MANUAL','MISSION','SNOOZE')")
    private DismissType dismissType;

    /**
     * 스누즈 누른 횟수
     */
    @Column(name = "snooze_count")
    @Builder.Default
    private Integer snoozeCount = 0;
}