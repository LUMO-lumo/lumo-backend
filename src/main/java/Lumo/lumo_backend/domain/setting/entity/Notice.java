package Lumo.lumo_backend.domain.setting.entity;

import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

/**
 * 공지사항 엔티티
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String type;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column( nullable = false)
    private boolean active;
    /**
     * soft delete 일자
     */
    private LocalDateTime deletedAt;

    public void update(String type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    public void softDelete() {
        active = false;
        deletedAt = LocalDateTime.now();
    }
}
