package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
