
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 알람 반복 요일 엔티티
 * - 알람이 반복될 요일 저장 (1:N)
 * - 월,수,금 반복이면 해당 알람에 3개 row
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_repeat_day")
public class AlarmRepeatDay {

    /**
     * 반복 설정 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repeat_id")
    private Long repeatId;

    /**
     * 소속 알람 (FK)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", nullable = false)
    private Alarm alarm;

    /**
     * 반복 요일 (MON~SUN)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", length = 10, nullable = false)
    private DayOfWeek dayOfWeek;
}