
package Lumo.lumo_backend.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission_content")
public class MissionContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;

    @Column(name = "mission_type", length = 20, nullable = false)
    private String missionType;

    @Column(name = "difficulty", length = 20, nullable = false)
    private String difficulty;

    @Column(name = "question", length = 500, nullable = false)
    private String question;

    @Column(name = "answer", length = 200, nullable = false)
    private String answer;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}