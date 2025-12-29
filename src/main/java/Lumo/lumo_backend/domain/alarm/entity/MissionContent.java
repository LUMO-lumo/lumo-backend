
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * 미션 문제 콘텐츠 엔티티
 * - 앱 전체 공용 문제은행 (독립 테이블)
 * - 미션 출제 시 여기서 랜덤 조회
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission_content")
public class MissionContent {

    /**
     * 문제 콘텐츠 고유 ID (PK)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    /**
     * 미션 유형 (MATH/OX_QUIZ/TYPING)
     * - WALK는 문제 콘텐츠 없음 (GPS 기반)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "mission_type", length = 20, nullable = false)
    private MissionType missionType;

    /**
     * 문제 난이도 (EASY/MEDIUM/HARD)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty", length = 20, nullable = false)
    private Difficulty difficulty;

    /**
     * 문제 내용
     * - MATH: "8+24+7=?"
     * - OX_QUIZ: "대한민국 수도는 서울이다"
     * - TYPING: "안녕하세요"
     */
    @Column(name = "question", length = 500, nullable = false)
    private String question;

    /**
     * 정답
     * - MATH: "39"
     * - OX_QUIZ: "O" 또는 "X"
     * - TYPING: "안녕하세요"
     */
    @Column(name = "answer", length = 200, nullable = false)
    private String answer;

    /**
     * 문제 등록일시
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}