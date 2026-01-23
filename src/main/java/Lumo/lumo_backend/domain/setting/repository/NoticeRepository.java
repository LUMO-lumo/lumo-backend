package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.entity.Notice;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByDeletedAtBefore(LocalDateTime deletedAtBefore);
}
