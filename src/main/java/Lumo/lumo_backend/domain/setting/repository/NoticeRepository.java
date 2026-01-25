package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.dto.NoticePreviewDTO;
import Lumo.lumo_backend.domain.setting.entity.Notice;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByDeletedAtBefore(LocalDateTime deletedAtBefore);

    List<NoticePreviewDTO> findAllByActiveOrderByCreatedAtDesc(boolean active);

    @Query("""
            SELECT n.id, n.type, n.title, n.createdAt FROM Notice n
            WHERE n.active = true
                and(
                    n.title LIKE %:keyword%
                    OR n.content LIKE %:keyword%
                    )
            ORDER BY n.createdAt DESC
            """)
    List<NoticePreviewDTO> search(String keyword);

}
