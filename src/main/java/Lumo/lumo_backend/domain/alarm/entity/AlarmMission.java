
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 알람 미션 설정 엔티티
 * - 알람별 미션 설정 저장 (1:1)
 * - 미션을 완료해야 알람 해제 가능
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm_mission")
public class AlarmMission {

    /**
     * 미션 설정 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    /**
     * 소속 알람 (FK, 1:1)
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id", unique = true, nullable = false)
    private Alarm alarm;

    /**
     * 미션 유형 (NONE/MATH/OX_QUIZ/TYPING/WALK)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "mission_type", columnDefinition = "ENUM('MATH','NONE','OX_QUIZ','TYPING','WALK')", nullable = false)
    @Builder.Default
    private MissionType missionType = MissionType.NONE;

    /**
     * 미션 난이도 (EASY/MEDIUM/HARD)
     * - WALK 타입은 난이도 없음
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", columnDefinition = "ENUM('EASY','HARD','MEDIUM')")
    private Difficulty difficulty;

    /**
     * 걷기 미션 목표 거리 (미터)
     * - WALK 타입일 때만 사용
     */
    @Column(name = "walk_goal_meter")
    private Integer walkGoalMeter;

    /**
     * 풀어야 할 문제 개수
     * - MATH, OX_QUIZ, TYPING 타입에서 사용
     * - 기본값: 1문제
     */
    @Column(name = "question_count")
    @Builder.Default
    private Integer questionCount = 1;
}