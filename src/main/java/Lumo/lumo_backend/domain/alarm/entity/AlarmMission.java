
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_mission")
public class AlarmMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", unique = true, nullable = false)
    private Alarm alarm;

    @Column(name = "mission_type", length = 20, nullable = false)
    private String missionType;

    @Column(name = "difficulty", length = 20)
    private String difficulty;

    @Column(name = "walk_goal_meter")
    private Integer walkGoalMeter;

    @Column(name = "question_count")
    @Builder.Default
    private Integer questionCount = 1;
}