
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 미션 수행 기록 엔티티
 * - 사용자의 미션 수행 결과 저장
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission_history")
public class MissionHistory {

    /**
     * 기록 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    /**
     * 수행한 알람 (FK)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", nullable = false)
    private Alarm alarm;

    /**
     * 수행한 미션 유형
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "mission_type", columnDefinition = "ENUM('MATH','NONE','OX_QUIZ','TYPING','WALK')", nullable = false)
    private MissionType missionType;

    /**
     * 미션 성공 여부
     */
    @Column(name = "is_success", nullable = false)
    private Boolean isSuccess;

    /**
     * 시도 횟수 (몇 번 만에 성공했는지)
     */
    @Column(name = "attempt_count")
    @Builder.Default
    private Integer attemptCount = 1;

    /**
     * 미션 완료 시간
     */
    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    /**
     * 기록 생성일시
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}