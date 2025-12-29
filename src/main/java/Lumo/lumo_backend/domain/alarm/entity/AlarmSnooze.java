
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_snooze")
public class AlarmSnooze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snooze_id")
    private Long snoozeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", unique = true, nullable = false)
    private Alarm alarm;

    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled = true;

    @Column(name = "interval_sec")
    @Builder.Default
    private Integer intervalSec = 300;

    @Column(name = "max_count")
    @Builder.Default
    private Integer maxCount = 3;
}